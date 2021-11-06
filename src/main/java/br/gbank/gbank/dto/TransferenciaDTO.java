package br.gbank.gbank.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class TransferenciaDTO implements Serializable {

    private long numeroContaOrigem;

    private long numeroContaDestino;

    private BigDecimal valor;

    public long getNumeroContaOrigem() {
        return numeroContaOrigem;
    }

    public void setNumeroContaOrigem(long numeroContaOrigem) {
        this.numeroContaOrigem = numeroContaOrigem;
    }

    public long getNumeroContaDestino() {
        return numeroContaDestino;
    }

    public void setNumeroContaDestino(long numeroContaDestino) {
        this.numeroContaDestino = numeroContaDestino;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

}
