package br.gbank.gbank.model.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

@Embeddable
public class DadosPessoais {

	@NotBlank(message = "Nome não informado")
	private String nome;
	@Column(unique = true)
	@NotBlank(message = "CPF não informado")
	@CPF(message = "CPF Inválido")
	private String cpf;
	@Email
	@NotBlank(message = "Email não informado")
	private String email;
	@Size(max = 20)
	private String telefone;

	public DadosPessoais() {
		super();
	}

	public DadosPessoais(@NotBlank String nome, @NotBlank String cpf, @Email @NotBlank String email,
			@Size(max = 20) String telefone) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, email);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DadosPessoais other = (DadosPessoais) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(email, other.email);
	}

	@Override
	public String toString() {
		return "DadosPessoais [nome=" + nome + ", cpf=" + cpf + ", email=" + email + ", telefone=" + telefone + "]";
	}

}
