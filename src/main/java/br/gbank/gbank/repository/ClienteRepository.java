package br.gbank.gbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gbank.gbank.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsClienteByDadosPessoaisCpf(String cpf);
}
