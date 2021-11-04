package br.gbank.gbank.service;

import br.gbank.gbank.dto.ClienteDTO;
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

	public Cliente create(ClienteDTO clienteDTO) {
		Cliente cliente = toCliente.apply(clienteDTO);
		return clienteRepository.save(cliente);
	}

	private Function<ClienteDTO, Cliente> toCliente = dto -> {
		Cliente cliente = new Cliente();
		cliente.getDadosPessoais().setNome(dto.getNome());
		cliente.getDadosPessoais().setEmail(dto.getEmail());
		return cliente;
	};
}
