package br.gbank.gbank.service;

import br.gbank.gbank.dto.ClienteDTO;
import br.gbank.gbank.exception.HandleException;
import br.gbank.gbank.model.Cliente;
import br.gbank.gbank.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Page<ClienteDTO> getAll(Pageable pageable) {
		return clienteRepository.findAll(pageable).map(ClienteDTO::toClienteDTO);
	}

	public Cliente create(ClienteDTO clienteDTO) throws HandleException {
		Cliente cliente = toCliente.apply(clienteDTO);
		String cpf = cliente.getDadosPessoais().getCpf();
		if (cpf == null || cpf.equals("")) {
			throw new HandleException("CPF não informado");
		} else if (clienteRepository.existsClienteByDadosPessoaisCpf(cliente.getDadosPessoais().getCpf())) {
			throw new HandleException("CPF já existe");
		}
		return clienteRepository.save(cliente);
	}

	private Function<ClienteDTO, Cliente> toCliente = dto -> {
		Cliente cliente = new Cliente();
		cliente.getDadosPessoais().setNome(dto.getNome());
		cliente.getDadosPessoais().setCpf(dto.getCpf());
		cliente.getDadosPessoais().setTelefone(dto.getTelefone());
		cliente.getDadosPessoais().setEmail(dto.getEmail());
		return cliente;
	};
}
