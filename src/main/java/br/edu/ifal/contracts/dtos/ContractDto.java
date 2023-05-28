package br.edu.ifal.contracts.dtos;

import br.edu.ifal.contracts.models.Contract;
import br.edu.ifal.contracts.models.Representative;

public record ContractDto(String title, CompanyDto contractedCompany, Representative representative) {

    public Contract mapToContract() {
        return new Contract(this.title(), this.contractedCompany().mapToCompany(), this.representative());
    }

}
