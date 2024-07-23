package br.edu.ifal.contracts.controllers;

import br.edu.ifal.contracts.dtos.CompanyDto;
import br.edu.ifal.contracts.dtos.ContractDto;
import br.edu.ifal.contracts.models.Company;
import br.edu.ifal.contracts.models.Contract;
import br.edu.ifal.contracts.repositories.CompaniesRepository;
import br.edu.ifal.contracts.repositories.ContractsRepository;
import br.edu.ifal.contracts.views.CompanyView;
import br.edu.ifal.contracts.views.ContractView;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@Validated
@RequestMapping("/v1/companies")
public class CompaniesController {
    private final CompaniesRepository companiesRepository;
    private final ContractsRepository contractsRepository;

    public CompaniesController(CompaniesRepository companiesRepository, ContractsRepository contractsRepository) {
        this.companiesRepository = companiesRepository;
        this.contractsRepository = contractsRepository;
    }
    @PostMapping
    public ResponseEntity<CompanyView> addCompany(@Valid @RequestBody CompanyDto companyDto) {
        return ResponseEntity.status(201).body(new CompanyView(this.companiesRepository.save(companyDto.mapToCompany())));
    }

    @PostMapping("{cnpj}/contracts")
    public ResponseEntity<?> addContractToCompany(@Valid @RequestBody ContractDto contractDto, @PathVariable String cnpj) {
        Optional<Company> optionalCompany = this.companiesRepository.findByCnpj(cnpj);
        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            Contract contract = this.contractsRepository.save(contractDto.mapToContract(company));
            return ResponseEntity.status(201).body(new ContractView(contract));
        } else {
            return ResponseEntity.status(404).body("Company not found");
        }
    }

    @GetMapping
    public List<CompanyView> getCompanies(){
        return this.companiesRepository.findAll().stream().map(CompanyView::new).toList();
    }
}
