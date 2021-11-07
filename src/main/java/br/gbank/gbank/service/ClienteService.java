package br.gbank.gbank.service;

import br.gbank.gbank.dto.ClienteCadastroDTO;
import br.gbank.gbank.exception.GenericException;
import br.gbank.gbank.exception.NotFoundException;
import br.gbank.gbank.model.entity.Cliente;
import br.gbank.gbank.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ContaService contaService;

    public Page<Cliente> getAll(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    public Cliente getById(Long id) {
        return clienteRepository.getClienteById(id).orElseThrow(() -> new NotFoundException(false, "cliente", "id", id.toString()));
    }

    @Transactional
    public Cliente create(ClienteCadastroDTO clienteDTO) {
        Cliente cliente = clienteDTO.toCliente();
        if (clienteRepository.existsClienteByDadosPessoaisCpf(cliente.getDadosPessoais().getCpf())) {
            throw new GenericException("CPF já cadastrado");
        }
        clienteRepository.save(cliente);
        contaService.create(cliente);

        return cliente;
    }
}
