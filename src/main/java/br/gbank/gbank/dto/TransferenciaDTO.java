package br.gbank.gbank.dto;

import java.io.Serializable;

public class TransferenciaDTO implements Serializable {

    private long contaDebitoId;

    private long contaCreditoId;

    private ValorMonetarioVO valor;

    public long getContaDebitoId() {
        return contaDebitoId;
    }

    public void setContaDebitoId(long contaDebitoId) {
        this.contaDebitoId = contaDebitoId;
    }

    public long getContaCreditoId() {
        return contaCreditoId;
    }

    public void setContaCreditoId(long contaCrecitoId) {
        this.contaCreditoId = contaCrecitoId;
    }

    public ValorMonetarioVO getValor() {
        return valor;
    }

    public void setValor(ValorMonetarioVO valor) {
        this.valor = valor;
    }

}
