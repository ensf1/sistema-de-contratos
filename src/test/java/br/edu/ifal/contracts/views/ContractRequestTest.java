package br.edu.ifal.contracts.views;

import br.edu.ifal.contracts.models.Contract;
import br.edu.ifal.contracts.models.Representative;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContractRequestTest {

    @Test
    @DisplayName("Should map to contract model")
    void shouldMapToContractModel() {

        var contractRequest = new ContractRequest(
                UUID.randomUUID().toString(),
                new CompanyRequest(
                        UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString()
                ),
                new Representative(UUID.randomUUID().toString(), UUID.randomUUID().toString())
        );

        Contract contract = contractRequest.mapToContract();
        assertEquals(contractRequest.title(), contract.getTitle());
        assertEquals(contractRequest.contractedCompany().name(),contract.getContractedCompany().getName());
        assertEquals(contractRequest.contractedCompany().address(), contract.getContractedCompany().getAddress());
        assertEquals(contractRequest.contractedCompany().cnpj(), contract.getContractedCompany().getCnpj());
        assertEquals(contractRequest.representative(),contract.getCompanyRepresentative());
    }

}