package br.edu.ifal.contracts.controllers;

import br.edu.ifal.contracts.dtos.CompanyDto;
import br.edu.ifal.contracts.dtos.ContractDto;
import br.edu.ifal.contracts.dtos.RepresentativeDto;
import br.edu.ifal.contracts.models.Company;
import br.edu.ifal.contracts.models.Contract;
import br.edu.ifal.contracts.repositories.CompaniesRepository;
import br.edu.ifal.contracts.repositories.ContractsRepository;
import br.edu.ifal.contracts.views.ContractView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static br.edu.ifal.contracts.TestUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ContractsControllerTest {
    private final ContractsRepository contractsRepository;
    private final CompaniesRepository companiesRepository;
    private ContractsController contractsController;

    @Autowired
    ContractsControllerTest(ContractsRepository contractsRepository, CompaniesRepository companiesRepository) {
        this.contractsRepository = contractsRepository;
        this.companiesRepository = companiesRepository;
    }

    @BeforeEach
    void setUp(){
        contractsRepository.deleteAll();
        companiesRepository.deleteAll();
        contractsController = new ContractsController(contractsRepository, companiesRepository);
    }

    @Test
    @DisplayName("Should return all contracts from database")
    void getContracts() {
        //arrange
        List<Company> companies = companiesRepository.saveAll(List.of(randomCompany(), randomCompany(), randomCompany()));
        Contract contract0 = randomContract(companies.get(0));
        Contract contract1 = randomContract(companies.get(1));
        Contract contract2 = randomContract(companies.get(2));
        contractsRepository.saveAll(List.of(contract0, contract1, contract2));

        //act
        List<ContractView> contracts = contractsController.getContracts();

        //assert
        assertEquals(new ContractView(contract0), contracts.get(0));
        assertEquals(new ContractView(contract1), contracts.get(1));
        assertEquals(new ContractView(contract2), contracts.get(2));
    }

    @Test
    @DisplayName("Should save contract to database")
    void addContract() {
        // arrange
        CompanyDto companyDto = randomCompanyDto();
        RepresentativeDto representativeDto = randomRepresentativeDto();
        ContractDto contractDto = randomContractDto(companyDto, representativeDto);


        // act
        contractsController.addContract(contractDto);

        // assert
        Company company = this.companiesRepository.findAll().get(0);
        assertEquals(companyDto.getName(), company.getName());
        assertEquals(companyDto.getAddress(), company.getAddress());
        assertEquals(companyDto.getCnpj(), company.getCnpj());

        Contract contract = this.contractsRepository.findAll().get(0);
        assertEquals(contractDto.title(), contract.getTitle());
        assertEquals(company.getId(), contract.getContractedCompany().getId());
        assertEquals(representativeDto.name(), contract.getCompanyRepresentative().getName());
        assertEquals(representativeDto.email(), contract.getCompanyRepresentative().getEmail());
    }

    @Test
    @DisplayName("Should delete contract from database")
    void shouldDeleteContractFromDatabase(){
        // arrange

        // act

        // assert

        Assertions.fail("Not implemented");
    }

}
