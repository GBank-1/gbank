package br.gbank.gbank.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "IBM")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "data_cadastro")
	private LocalDate dataCadastro;
	@Embedded
	private DadosPessoais dadosPessoais;

	public Cliente() {
		super();
		dataCadastro = LocalDate.now();
		dadosPessoais = new DadosPessoais();
	}

	public Cliente(DadosPessoais dadosPessoais) {
		super();
		this.dadosPessoais = dadosPessoais;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDtCadastro() {
		return dataCadastro;
	}

	public void setDtCadastro(LocalDate dtCadastro) {
		this.dataCadastro = dtCadastro;
	}

	public DadosPessoais getDadosPessoais() {
		return dadosPessoais;
	}

	public void setDadosPessoais(DadosPessoais dadosPessoais) {
		this.dadosPessoais = dadosPessoais;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", dtCadastro=" + dataCadastro + ", dadosPessoais=" + dadosPessoais + "]";
	}

}
