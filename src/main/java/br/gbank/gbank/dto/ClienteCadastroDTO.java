package br.gbank.gbank.dto;

import br.gbank.gbank.model.entity.Cliente;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class ClienteCadastroDTO implements Serializable {
    @NotBlank(message = "Nome não informado")
    private String nome;
    @NotBlank(message = "CPF não informado")
    @CPF(message = "CPF Inválido")
    private String cpf;
    @Email
    @NotBlank(message = "Email não informado")
    private String email;
    @Size(max = 20)
    private String telefone;

    public static ClienteDTO fromCliente(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNome(cliente.getDadosPessoais().getNome());
        clienteDTO.setEmail(cliente.getDadosPessoais().getEmail());
        return clienteDTO;
    }

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Cliente toCliente() {
        Cliente cliente = new Cliente();
        cliente.getDadosPessoais().setNome(this.getNome());
        cliente.getDadosPessoais().setCpf(this.getCpf());
        cliente.getDadosPessoais().setTelefone(this.getTelefone());
        cliente.getDadosPessoais().setEmail(this.getEmail());
        return cliente;
    }
}
