package br.edu.ifal.contracts.controllers;

import br.edu.ifal.contracts.dtos.ContractDto;
import br.edu.ifal.contracts.repositories.CompaniesRepository;
import br.edu.ifal.contracts.repositories.ContractsRepository;
import br.edu.ifal.contracts.views.ContractView;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ContractsController {
    private final ContractsRepository contractsRepository;
    private final CompaniesRepository companiesRepository;

    public ContractsController(ContractsRepository contractsRepository, CompaniesRepository companiesRepository) {
        this.contractsRepository = contractsRepository;
        this.companiesRepository = companiesRepository;
    }

    public List<ContractView> getContracts() {
        return this.contractsRepository.findAll().stream().map(ContractView::new).toList();
    }

    public void addContract(ContractDto contractDto) {
        this.contractsRepository.save(
                contractDto.mapToContract(
                        this.companiesRepository.save(contractDto.contractedCompany().mapToCompany())
                )
        );
    }
}
