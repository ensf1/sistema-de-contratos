package br.edu.ifal.contracts.dtos;

import br.edu.ifal.contracts.models.Company;
import br.edu.ifal.contracts.models.Contract;

public record ContractDto(String title, CompanyDto contractedCompany, RepresentativeDto representative) {

    public Contract mapToContract(Company company) {
        return new Contract(this.title(), company, this.representative().mapToRepresentative());
    }

}
