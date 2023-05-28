package br.edu.ifal.contracts.controllers;

import br.edu.ifal.contracts.dtos.ContractDto;
import br.edu.ifal.contracts.repositories.CompanyRepository;
import br.edu.ifal.contracts.repositories.ContractsRepository;
import br.edu.ifal.contracts.views.ContractView;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ContractsController {
    private final ContractsRepository contractsRepository;
    private final CompanyRepository companyRepository;

    public ContractsController(ContractsRepository contractsRepository, CompanyRepository companyRepository) {
        this.contractsRepository = contractsRepository;
        this.companyRepository = companyRepository;
    }

    public List<ContractView> getContracts() {
        return this.contractsRepository.findAll().stream().map(ContractView::new).toList();
    }

    public void addContract(ContractDto contractDto) {
        this.contractsRepository.save(contractDto.mapToContract());
    }
}
