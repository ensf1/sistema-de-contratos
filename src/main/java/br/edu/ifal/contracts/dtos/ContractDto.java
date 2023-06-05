package br.edu.ifal.contracts.dtos;

import br.edu.ifal.contracts.models.*;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public record ContractDto(@NotEmpty String title, String number, ContractType type, CompanyDto contractedCompany, RepresentativeDto representative, String start, String end) {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public Contract mapToContract(Company company) {
        if (type == ContractType.SERVICES){
            return new ContractOfServices(this.title(), number,company, this.representative().mapToRepresentative(), LocalDate.parse(start, formatter), LocalDate.parse(end, formatter));
        }
        return new ContractOfGoodsAndMaterials(this.title(), number,company, this.representative().mapToRepresentative(), LocalDate.parse(start, formatter), LocalDate.parse(end, formatter));
    }

}
