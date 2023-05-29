package br.edu.ifal.contracts.views;

import br.edu.ifal.contracts.dtos.CompanyDto;
import br.edu.ifal.contracts.dtos.ContractDto;
import br.edu.ifal.contracts.dtos.RepresentativeDto;
import br.edu.ifal.contracts.models.Contract;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContractDtoTest {

    @Test
    @DisplayName("Should map to contract model")
    void shouldMapToContractModel() {

        CompanyDto companyDto = new CompanyDto(
                UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString()
        );
        var contractDto = new ContractDto(
                UUID.randomUUID().toString(),
                companyDto,
                new RepresentativeDto(UUID.randomUUID().toString(), UUID.randomUUID().toString())
        );

        Contract contract = contractDto.mapToContract(companyDto.mapToCompany());
        assertEquals(contractDto.title(), contract.getTitle());
        assertEquals(contractDto.contractedCompany().name(),contract.getContractedCompany().getName());
        assertEquals(contractDto.contractedCompany().address(), contract.getContractedCompany().getAddress());
        assertEquals(contractDto.contractedCompany().cnpj(), contract.getContractedCompany().getCnpj());
        assertEquals(contractDto.representative().mapToRepresentative(),contract.getCompanyRepresentative());
    }

}
