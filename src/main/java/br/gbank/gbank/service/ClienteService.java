package br.gbank.gbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.gbank.gbank.dto.ClienteCadastroDTO;
import br.gbank.gbank.dto.ClienteDTO;
import br.gbank.gbank.exception.HandleException;
import br.gbank.gbank.model.entity.Cliente;
import br.gbank.gbank.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ContaService contaService;

	public Page<ClienteDTO> getAll(Pageable pageable) {
		return clienteRepository.findAll(pageable).map(ClienteDTO::fromCliente);
	}

	public Cliente create(ClienteCadastroDTO clienteDTO) throws HandleException {
		Cliente cliente = clienteDTO.toCliente();
		String cpf = cliente.getDadosPessoais().getCpf();
		if (cpf == null || cpf.equals("")) {
			throw new HandleException("CPF não informado");
		} else if (clienteRepository.existsClienteByDadosPessoaisCpf(cliente.getDadosPessoais().getCpf())) {
			throw new HandleException("CPF já existe");
		}
		clienteRepository.save(cliente);
		contaService.create(cliente);

		return cliente;

	}

    public Cliente getById(Long id) {
        return clienteRepository.getById(id);
    }


}
