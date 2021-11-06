package br.gbank.gbank.dto;

import java.io.Serializable;

import br.gbank.gbank.model.entity.Conta;

public class ContaDTO implements Serializable {
    private Long id;
    private Long numero;
    private ValorMonetarioVO saldo;
    private Long clienteId;
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

    public ValorMonetarioVO getSaldo() {
        return saldo;
    }

    public void setSaldo(ValorMonetarioVO saldo) {
        this.saldo = saldo;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
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
        contaDTO.setSaldo(ValorMonetarioVO.fromMonetaryAmount(conta.getSaldo()));

        contaDTO.setClienteId(conta.getCliente().getContaId());
        contaDTO.setAtiva(conta.isAtiva());
        return contaDTO;
    }
}
