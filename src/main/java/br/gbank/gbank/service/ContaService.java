package br.gbank.gbank.service;

import br.gbank.gbank.exception.NotFoundException;
import br.gbank.gbank.model.entity.Cliente;
import br.gbank.gbank.model.entity.Conta;
import br.gbank.gbank.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public Page<Conta> getAll(Pageable pageable) {
        return contaRepository.findAll(pageable);
    }

    public Conta getById(Long id) {
        return contaRepository.getContaById(id).orElseThrow(() -> new NotFoundException(true, "conta", "id", id.toString()));
    }

    public Conta getByNumero(Long numero) {
        return contaRepository.getContaByNumero(numero).orElseThrow(() -> new NotFoundException(true, "conta", "numero", numero.toString()));
    }

    public void create(Cliente cliente) {
        Conta conta = new Conta(cliente);
        contaRepository.save(conta);
    }
}
