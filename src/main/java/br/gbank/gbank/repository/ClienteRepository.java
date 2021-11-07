package br.gbank.gbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gbank.gbank.model.entity.Cliente;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> getClienteById(Long id);
    boolean existsClienteByDadosPessoaisCpf(String cpf);
}
