package br.edu.ifal.contracts.models;

import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class ContractOfServices extends Contract{
    public ContractOfServices(
            String title,
            String number,
            Company contractedCompany,
            Representative companyRepresentative,
            LocalDate start,
            LocalDate end
    ) {
        super(title, number, contractedCompany, companyRepresentative, start, end);
        setType(ContractType.SERVICES);
    }

    public ContractOfServices() { //JPA

    }

    @Override
    public LocalDate nextPayment() {
        LocalDate now = LocalDate.now();
        return now.withDayOfMonth(5).plusMonths(1);
    }
}
