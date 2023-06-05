package br.edu.ifal.contracts.controllers;

import br.edu.ifal.contracts.dtos.ContractDto;
import br.edu.ifal.contracts.models.Contract;
import br.edu.ifal.contracts.repositories.CompaniesRepository;
import br.edu.ifal.contracts.repositories.ContractsRepository;
import br.edu.ifal.contracts.views.ContractView;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/v1/contracts")
public class ContractsController {
    private final ContractsRepository contractsRepository;
    private final CompaniesRepository companiesRepository;

    public ContractsController(ContractsRepository contractsRepository, CompaniesRepository companiesRepository) {
        this.contractsRepository = contractsRepository;
        this.companiesRepository = companiesRepository;
    }
    @PostMapping
    public ResponseEntity<ContractView> addContract(@RequestBody ContractDto contractDto) {
        return ResponseEntity.status(201).body(new ContractView(this.contractsRepository.save(
                contractDto.mapToContract(
                        this.companiesRepository.save(contractDto.contractedCompany().mapToCompany())
                ))));
    }
    @GetMapping
    public List<ContractView> getContracts() {
        return this.contractsRepository.findAll().stream().map(ContractView::new).toList();
    }

    @DeleteMapping("{number}")
    @Transactional
    public ResponseEntity<Object> deleteContract(@PathVariable String number){
        this.contractsRepository.deleteByNumber(number);
        return ResponseEntity.status(204).build();
    }

    @PatchMapping("{number}")
    @Transactional
    public ResponseEntity<Object> updateContract(@PathVariable String number, @RequestBody ContractDto contractDto){
        var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Contract contract = contractsRepository.findByNumber(number);

        if (null != contractDto.representative()){
            if (!contractDto.representative().name().equals(contract.getCompanyRepresentative().getName())){
                contract.getCompanyRepresentative().setName(contractDto.representative().name());
            }
            if (!contractDto.representative().email().equals(contract.getCompanyRepresentative().getEmail())){
                contract.getCompanyRepresentative().setEmail(contractDto.representative().email());
            }
        }

        if (null != contractDto.end() && !contractDto.end().equals(contract.getEnd().format(formatter))){
            contract.setEnd(LocalDate.parse(contractDto.end(), formatter));
        }
        this.contractsRepository.save(contract);
        return ResponseEntity.status(204).build();

    }
}
