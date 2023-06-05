package br.edu.ifal.contracts.repositories;

import br.edu.ifal.contracts.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompaniesRepository extends JpaRepository<Company,Long> {
    Company findByCnpj(String cnpj);
}
