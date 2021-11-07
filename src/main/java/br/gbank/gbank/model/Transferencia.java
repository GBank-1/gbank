package br.gbank.gbank.model;

import java.time.LocalDateTime;

import javax.money.MonetaryAmount;
import javax.validation.constraints.NotNull;

import br.gbank.gbank.exception.ContaSemSaldoException;
import br.gbank.gbank.model.entity.Conta;

public class Transferencia extends Transation {

    @NotNull(message = "A conta de destino não pode ser nula")
    private Conta destino;

    @NotNull(message = "A conta de origem não pode ser nula")
    private Conta origem;

    @NotNull(message = "Valor não pode ser nulo")
    private MonetaryAmount valor;

    @NotNull(message = "Taxa não pode ser nula")
    private TaxaTransferencia taxa;

    public Transferencia(Conta destino, Conta origem, MonetaryAmount valor, TaxaTransferencia taxa) {
        super(TipoTransacao.TRANSFERENCIA);
        this.destino = destino;
        this.origem = origem;
        this.valor = valor;
        this.taxa = taxa;
    }

    @Override
    public void processar() throws ContaSemSaldoException {
        MonetaryAmount taxaCalculada = taxa.calcular(valor);
        origem.debitar(valor.add(taxaCalculada));
        destino.creditar(valor);
        dataHoraSolicitada= LocalDateTime.now();        
    }

    public Conta getDestino() {
        return destino;
    }

    public Conta getOrigem() {
        return origem;
    }

    public MonetaryAmount getValor() {
        return valor;
    }

    public TaxaTransferencia getTaxa() {
        return taxa;
    }
}
