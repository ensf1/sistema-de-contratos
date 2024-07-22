package br.edu.ifal.contracts.repositories;

import br.edu.ifal.contracts.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompaniesRepository extends JpaRepository<Company,Long> {
    Optional<Company> findByCnpj(String cnpj);
}
