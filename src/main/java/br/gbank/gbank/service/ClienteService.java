package br.gbank.gbank.service;

import br.gbank.gbank.dto.ClienteCadastroDTO;
import br.gbank.gbank.dto.ClienteUpdateDTO;
import br.gbank.gbank.exception.GenericException;
import br.gbank.gbank.exception.NotFoundException;
import br.gbank.gbank.model.entity.Cliente;
import br.gbank.gbank.model.entity.DadosPessoais;
import br.gbank.gbank.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.function.Function;

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

    public void update(Long id, ClienteUpdateDTO clienteUpdateDTO){
        Cliente cliente =  clienteRepository.getClienteById(id).orElseThrow(() -> new NotFoundException(false, "cliente", "id", id.toString()));
        updateValues(cliente, clienteUpdateDTO);
        clienteRepository.save(cliente);
    }

    private void updateValues(Cliente cliente, ClienteUpdateDTO clienteUpdateDTO){
        DadosPessoais dadosPessoais = new DadosPessoais();
        dadosPessoais.setNome(clienteUpdateDTO.getNome());
        dadosPessoais.setCpf(cliente.getDadosPessoais().getCpf());
        dadosPessoais.setTelefone(clienteUpdateDTO.getTelefone());
        dadosPessoais.setEmail(clienteUpdateDTO.getEmail());
        cliente.setDadosPessoais(dadosPessoais);
    }
}
