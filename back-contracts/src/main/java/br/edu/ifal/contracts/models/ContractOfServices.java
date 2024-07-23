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
    public LocalDate nextPayment() throws ContractAlreadyEndedException {
        LocalDate now = LocalDate.now();
        if (getEnd().isBefore(now)) {
            throw new ContractAlreadyEndedException("Contract already ended - contract end date is before today");
        }
        return now.withDayOfMonth(5).plusMonths(1);
    }
}
