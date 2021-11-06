package br.gbank.gbank.dto;

import javax.money.MonetaryAmount;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.gbank.gbank.model.entity.Conta;


public class ContaDTO {
    private Long id;
    private Long numero;
    private MonetaryAmount saldo;
    private ClienteDTO clienteDTO;
    private boolean ativa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }
    
    public MonetaryAmount getSaldo() {
        return saldo;
    }

    public void setSaldo(MonetaryAmount saldo) {
        this.saldo = saldo;
    }

    public ClienteDTO getClienteDTO() {
        return clienteDTO;
    }

    public void setClienteDTO(ClienteDTO clienteDTO) {
        this.clienteDTO = clienteDTO;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public static ContaDTO fromConta(Conta conta) {
        ContaDTO contaDTO = new ContaDTO();
        contaDTO.setId(conta.getId());
        contaDTO.setNumero(conta.getNumero());
        contaDTO.setSaldo(conta.getSaldo());

        contaDTO.setClienteDTO(ClienteDTO.fromCliente(conta.getCliente()));
        contaDTO.setAtiva(conta.isAtiva());
        return contaDTO;
    }
}
