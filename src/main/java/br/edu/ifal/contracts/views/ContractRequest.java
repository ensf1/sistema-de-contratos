package br.edu.ifal.contracts.views;

import br.edu.ifal.contracts.models.Contract;
import br.edu.ifal.contracts.models.Representative;

public record ContractRequest(String title, CompanyRequest contractedCompany, Representative representative) {

    public Contract mapToContract() {
        return new Contract(this.title(), this.contractedCompany().mapToCompany(), this.representative());
    }

}
