package br.gbank.gbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.gbank.gbank.repository.ContaRepository;
import br.gbank.gbank.model.Cliente;
import br.gbank.gbank.model.Conta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public Page<Conta> getAll(Pageable pageable) {
		return contaRepository.findAll(pageable);
	}

    public Conta create(Cliente cliente) {
        Conta conta = new Conta();
        conta.setCliente(cliente);
        return contaRepository.save(conta);
    }
    
}