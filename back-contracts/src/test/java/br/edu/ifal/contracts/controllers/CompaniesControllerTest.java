package br.edu.ifal.contracts.controllers;

import br.edu.ifal.contracts.dtos.CompanyDto;
import br.edu.ifal.contracts.models.Company;
import br.edu.ifal.contracts.repositories.CompaniesRepository;
import br.edu.ifal.contracts.repositories.ContractsRepository;
import br.edu.ifal.contracts.views.CompanyView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CompaniesControllerTest {

    private final CompaniesRepository companiesRepository;
    private final ContractsRepository contractsRepository;
    private CompaniesController companiesController;

    @Autowired
    CompaniesControllerTest(CompaniesRepository companiesRepository, ContractsRepository contractsRepository) {
        this.companiesRepository = companiesRepository;
        this.contractsRepository = contractsRepository;
    }

    @BeforeEach
    void setUp(){
        contractsRepository.deleteAll();
        companiesRepository.deleteAll();
        companiesController = new CompaniesController(companiesRepository, contractsRepository);
    }

    @Test
    @DisplayName("Should save company to database")
    void addCompany() {
        // arrange
        CompanyDto companyDto = randomCompanyDto();

        //act
        companiesController.addCompany(companyDto);

        //assert
        Company company = this.companiesRepository.findAll().get(0);
        assertEquals(companyDto.getName(), company.getName());
        assertEquals(companyDto.getAddress(), company.getAddress());
        assertEquals(companyDto.getCnpj(), company.getCnpj());
    }


    @Test
    @DisplayName("Should return all companies from database")
    void shouldReturnAllCompaniesFromDatabase(){
        // arrange
        List<Company> companyList = companiesRepository.saveAll(List.of(randomCompany(),randomCompany(),randomCompany()));

        // act
        List<CompanyView> companyViewList = companiesController.getCompanies();

        // assert
        assertEquals(companyList.get(0).getName(), companyViewList.get(0).name());
        assertEquals(companyList.get(0).getAddress(), companyViewList.get(0).address());
        assertEquals(companyList.get(0).getCnpj(), companyViewList.get(0).cnpj());

    }

    @Test
    @DisplayName("Should fail when adding a company with invalid name")
    void shouldFailWhenAddingACompanyWithInvalidName(){
        // arrange
        CompanyDto companyWithEmptyName = new CompanyDto("", randomString(), randomString());

        // act and assert
//        assertEquals(companiesController.addCompany(companyWithEmptyName) , ResponseEntity.status(201).build());

    }

    private String randomString(){
        return UUID.randomUUID().toString();
    }
    private CompanyDto randomCompanyDto() {
        return new CompanyDto(randomString(), randomString(), randomString());
    }
    private Company randomCompany() {
        return new Company(randomString(), randomString(), randomString());
    }
}
