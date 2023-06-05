package br.edu.ifal.contracts.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static br.edu.ifal.contracts.TestUtils.randomCompany;
import static br.edu.ifal.contracts.TestUtils.randomContract;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ContractTest {
    @Test
    @DisplayName("Should return the next payment date for goods and materials")
    void shouldReturnTheNextPaymentDateForGoodsAndMaterials() throws Contract.ContractAlreadyEndedException {
        // arrange
        Contract contract = randomContract(randomCompany(), ContractType.GOODS_AND_MATERIALS);
        var endDate = LocalDate.of(2024, 8, 1);
        contract.setEnd(endDate);

        // act
        var nextPayment = contract.nextPayment();

        // assert
        assertEquals(nextPayment.getDayOfMonth(), 5);
        assertEquals(nextPayment.getMonthValue(), 1);
        assertEquals(nextPayment.getYear(), 2024);
    }

    @Test
    @DisplayName("Should return next payment date for services")
    void shouldReturnNextPaymentDateForServices() throws Contract.ContractAlreadyEndedException {
        // arrange
        Contract contract = randomContract(randomCompany(), ContractType.SERVICES);
        var endDate = LocalDate.of(2024, 8, 1);
        contract.setEnd(endDate);

        // act
        var nextPayment = contract.nextPayment();

        // assert
        assertEquals(nextPayment.getDayOfMonth(), 5);
        assertEquals(nextPayment.getMonthValue(), 7);
        assertEquals(nextPayment.getYear(), 2023);
    }

    @Test
    @DisplayName("Should throw exception when getting next payment for services contract that already ended")
    void shouldThrowExceptionWhenGettingNextPaymentForServicesContractThatAlreadyEnded() {
        // arrange
        Contract contract = randomContract(randomCompany(), ContractType.SERVICES);
        var endDate = LocalDate.of(2023, 5, 1);
        contract.setEnd(endDate);

        // act and assert
        assertThrows(Contract.ContractAlreadyEndedException.class, () -> contract.nextPayment());
    }
}
