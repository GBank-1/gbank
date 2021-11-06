package br.gbank.gbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.gbank.gbank.model.entity.Cliente;
import br.gbank.gbank.model.entity.Conta;
import br.gbank.gbank.repository.ContaRepository;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public Page<Conta> getAll(Pageable pageable) {
        return contaRepository.findAll(pageable);
    }

    public Conta create(Cliente cliente) {
        Conta entity = new Conta(cliente);
        cliente.setConta(entity);
        return contaRepository.save(entity);
    }

    public Conta getById(Long id) {
        return contaRepository.getById(id);
    }

    public Conta getByNumero(Long numero) {
        return contaRepository.getByNumero(numero);
    }
    
}
