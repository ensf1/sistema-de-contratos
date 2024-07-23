package br.edu.ifal.contracts.views;

import br.edu.ifal.contracts.models.Contract;
import br.edu.ifal.contracts.models.ContractType;

import java.time.format.DateTimeFormatter;

public record ContractView(
        String title,
        String number,
        ContractType type,
        CompanyView contractedCompany,
        RepresentativeView representative,
        String start,
        String end,
        String nextPayment
) {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public ContractView(Contract contract) {
        this(
                contract.getTitle(),
                contract.getNumber(),
                contract.getType(),
                new CompanyView(contract.getContractedCompany()),
                new RepresentativeView(contract.getCompanyRepresentative()),
                contract.getStart().format(formatter),
                contract.getEnd().format(formatter),
                nextPayment(contract)
        );
    }

    private static String nextPayment(Contract contract) {
        try {
            return contract.nextPayment().format(formatter);
        } catch (Contract.ContractAlreadyEndedException exception) {
            System.out.println(exception.getMessage());
            return "FINALIZADO";
        }
    }
}
