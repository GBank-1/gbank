package br.com.gbank.service;

import br.com.gbank.dto.ClienteDTO;
import br.com.gbank.model.Cliente;
import br.com.gbank.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Page<Cliente> getAll(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    public Cliente create(ClienteDTO clienteDTO){
        Cliente cliente = toCliente.apply(clienteDTO);
        return clienteRepository.save(cliente);
    }

    private Function<ClienteDTO, Cliente> toCliente = dto -> {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setPassword(dto.getPassword());
        return cliente;
    };
}
