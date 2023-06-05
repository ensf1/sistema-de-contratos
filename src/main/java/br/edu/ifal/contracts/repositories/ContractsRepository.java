package br.edu.ifal.contracts.repositories;

import br.edu.ifal.contracts.models.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractsRepository extends JpaRepository<Contract,Long> {

    void deleteByNumber(String number);
    Contract findByNumber(String number);
}
