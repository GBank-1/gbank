package br.gbank.gbank.model;

import java.time.LocalDateTime;

import br.gbank.gbank.exception.ContaSemSaldoException;

public abstract class Transation {

    protected LocalDateTime dataHoraSolicitada;

    protected LocalDateTime dataHoraEfetuada;

    private TipoTransacao tipo;

    protected Transation(TipoTransacao tipo) {
        this.tipo = tipo;
    }

    public abstract void processar() throws ContaSemSaldoException;
    
    public LocalDateTime getDataHoraSolicitada() {
        return dataHoraSolicitada;
    }

    public LocalDateTime getDataHoraEfetuada() {
        return dataHoraEfetuada;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

}
