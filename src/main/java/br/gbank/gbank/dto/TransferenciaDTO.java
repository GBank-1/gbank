package br.gbank.gbank.dto;

import javax.validation.Valid;
import javax.validation.constraints.AssertFalse;
import java.io.Serializable;

public class TransferenciaDTO implements Serializable {

    private long contaDebitoId;

    private long contaCreditoId;

    @Valid
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

    @AssertFalse(message = "As contas de origem e destino não podem ser a mesma")
    private boolean isValid() {
        return this.contaCreditoId == this.contaDebitoId;
    }
}
