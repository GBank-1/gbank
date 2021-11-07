package br.gbank.gbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gbank.gbank.model.entity.Conta;

import java.util.Optional;

public interface ContaRepository extends JpaRepository<Conta, Long> {
    Optional<Conta> getContaById(Long id);
    Optional<Conta> getContaByNumero(Long numero);
}
