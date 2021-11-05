package br.gbank.gbank.dto;

import br.gbank.gbank.model.Cliente;

public class ClienteDTO {
	private String nome;
	private String email;
	private String cpf;
	private String telefone;


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public void getCpf() {
		return cpf;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public static ClienteDTO toClienteDTO(Cliente cliente) {
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setNome(cliente.getDadosPessoais().getNome());
		clienteDTO.setEmail(cliente.getDadosPessoais().getEmail());
		return clienteDTO;
	}

	
	

}
