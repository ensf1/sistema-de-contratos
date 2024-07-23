package br.edu.ifal.contracts.models;

import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class ContractOfGoodsAndMaterials extends Contract{
    public ContractOfGoodsAndMaterials(
            String title,
            String number,
            Company contractedCompany,
            Representative companyRepresentative,
            LocalDate start,
            LocalDate end
    ) {
        super(title, number, contractedCompany, companyRepresentative, start, end);
        setType(ContractType.GOODS_AND_MATERIALS);
    }

    public ContractOfGoodsAndMaterials() { //JPA

    }

    @Override
    public LocalDate nextPayment() {
        LocalDate now = LocalDate.now();
        LocalDate nextPayment = now.withMonth(1).plusYears(1).withDayOfMonth(5);
        if(nextPayment.isAfter(getEnd())) {
            return getEnd();
        }
        return nextPayment;
    }
}
