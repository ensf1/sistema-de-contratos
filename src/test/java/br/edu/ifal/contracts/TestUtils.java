package br.edu.ifal.contracts;

import br.edu.ifal.contracts.dtos.CompanyDto;
import br.edu.ifal.contracts.dtos.ContractDto;
import br.edu.ifal.contracts.dtos.RepresentativeDto;
import br.edu.ifal.contracts.models.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;

public class TestUtils {
    public static RepresentativeDto randomRepresentativeDto() {
        return new RepresentativeDto(randomString(), randomString());
    }

    public static CompanyDto randomCompanyDto() {
        return new CompanyDto(
                randomString(), randomString(), randomString()
        );
    }
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static ContractType randomContractType() {
        return ContractType.values()[randomInt(1)];
    }

    public static String randomString() {
        return UUID.randomUUID().toString();
    }

    public static int randomInt(int bound) {
        return randomInt(0, bound);
    }

    public static int randomInt(int origin, int bound) {
        return new Random().nextInt(origin, bound);
    }

    public static LocalDate randomDate() {
        return LocalDate.of(randomInt(2023, 2030), randomInt(1, 12), randomInt(1, 28));
    }

    public static String randomDateString(){
        return randomDate().format(formatter);
    }

    public static Contract randomContract(Company company) {
        if (randomInt(1) == 1) {
            return new ContractOfServices(randomString(), randomString(), company, randomRepresentative(), randomDate(), randomDate());
        }
        return new ContractOfGoodsAndMaterials(randomString(), randomString(), company, randomRepresentative(), randomDate(), randomDate());
    }

    public static Representative randomRepresentative() {
        return new Representative(randomString(), randomString());
    }

    public static ContractDto randomContractDto() {
        return new ContractDto(randomString(), randomString(), randomContractType(), randomCompanyDto(), randomRepresentativeDto(), randomDateString(), randomDateString());
    }

    public static ContractDto randomContractDto(CompanyDto companyDto) {
        return new ContractDto(randomString(), randomString(), randomContractType(), companyDto, randomRepresentativeDto(), randomDateString(), randomDateString());
    }

    public static ContractDto randomContractDto(CompanyDto companyDto, RepresentativeDto representativeDto) {
        return new ContractDto(randomString(), randomString(), randomContractType(), companyDto, representativeDto, randomDateString(), randomDateString());
    }

    public static Company randomCompany() {
        return new Company(randomString(), randomString(), randomString());
    }

}
