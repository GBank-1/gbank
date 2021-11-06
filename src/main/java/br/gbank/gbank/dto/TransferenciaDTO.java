package br.gbank.gbank.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class TransferenciaDTO implements Serializable {

    private long contaDebitoId;

    private long contaCreditoId;

    private BigDecimal valor;

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

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

}
