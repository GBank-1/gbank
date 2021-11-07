package br.gbank.gbank.dto;

import br.gbank.gbank.model.entity.Cliente;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class ClienteUpdateDTO implements Serializable {
	@NotBlank(message = "Nome não informado")
	private String nome;
	@Email
	@NotBlank(message = "Email não informado")
	private String email;
	@Size(max = 20)
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

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getTelefone() {
		return telefone;
	}

	public Cliente toCliente() {
		Cliente cliente = new Cliente();
		cliente.getDadosPessoais().setNome(this.getNome());
		cliente.getDadosPessoais().setTelefone(this.getTelefone());
		cliente.getDadosPessoais().setEmail(this.getEmail());
		return cliente;
	}
}
