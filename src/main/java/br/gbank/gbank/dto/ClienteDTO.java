package br.gbank.gbank.dto;

import java.io.Serializable;
import java.time.LocalDate;

import br.gbank.gbank.model.entity.Cliente;

public class ClienteDTO implements Serializable{
	private Long id;
	private String nome;
	private String email;
	private String cpf;
	private LocalDate dataCadastro;
	private String telefone;
	private Long contaId;


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
	
	public String getCpf() {
		return cpf;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public Long getContaId() {
		return contaId;
	}

	public void setContaId(Long contaId) {
		this.contaId = contaId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public static ClienteDTO fromCliente(Cliente cliente) {
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setId(cliente.getId());
		clienteDTO.setCpf(cliente.getDadosPessoais().getCpf());
		clienteDTO.setDataCadastro(cliente.getDataCadastro());
		clienteDTO.setNome(cliente.getDadosPessoais().getNome());
		clienteDTO.setEmail(cliente.getDadosPessoais().getEmail());
		clienteDTO.setContaId(cliente.getContaId());
		return clienteDTO;
	}	

	

}
