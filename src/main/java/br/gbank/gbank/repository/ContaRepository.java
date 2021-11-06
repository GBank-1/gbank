package br.gbank.gbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gbank.gbank.model.entity.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {

    @Query("Select c from Conta c where numero = :numero")
    Conta getByNumero(Long numero);
    
}
