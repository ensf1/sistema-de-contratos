package br.edu.ifal.contracts.views;

import br.edu.ifal.contracts.dtos.CompanyDto;
import br.edu.ifal.contracts.dtos.ContractDto;
import br.edu.ifal.contracts.models.Contract;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.edu.ifal.contracts.TestUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ContractDtoTest {

    @Test
    @DisplayName("Should map to contract model")
    void shouldMapToContractModel() {

        CompanyDto companyDto = randomCompanyDto();
        var contractDto = new ContractDto(
                randomString(),
                randomString(),
                randomContractType(),
                companyDto,
                randomRepresentativeDto(),
                randomDateString(),
                randomDateString()
        );

        Contract contract = contractDto.mapToContract(companyDto.mapToCompany());
        assertEquals(contractDto.title(), contract.getTitle());
        assertEquals(contractDto.number(), contract.getNumber());
        assertEquals(contractDto.type(), contract.getType());
        assertEquals(contractDto.contractedCompany().getName(),contract.getContractedCompany().getName());
        assertEquals(contractDto.contractedCompany().getAddress(), contract.getContractedCompany().getAddress());
        assertEquals(contractDto.contractedCompany().getCnpj(), contract.getContractedCompany().getCnpj());
        assertEquals(contractDto.representative().mapToRepresentative().getName(),contract.getCompanyRepresentative().getName());
        assertEquals(contractDto.representative().mapToRepresentative().getEmail(),contract.getCompanyRepresentative().getEmail());
        assertEquals(contractDto.start(),contract.getStart().format(formatter));
        assertEquals(contractDto.end(),contract.getEnd().format(formatter));
    }



}
