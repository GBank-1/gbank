package br.gbank.gbank.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.money.MonetaryAmount;

import org.javamoney.moneta.FastMoney;

import br.gbank.gbank.exception.ContaSemSaldoException;
import br.gbank.gbank.model.entity.Conta;
import br.gbank.gbank.util.MonetaryUtil;

public class Transferencia extends Transation {

    private Conta destino;

    private Conta origem;

    private MonetaryAmount valor;

    private TaxaTransferencia taxa;

    protected Transferencia() {
        super(TipoTransacao.TRANSFERENCIA);
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

    public static class Builder {

        private Transferencia transferencia;

        public Builder() {
            transferencia = new Transferencia();
        }

        public Builder from(Conta origem) {
            transferencia.origem = origem;
            return this;
        }

        public Builder to(Conta destino) {
            transferencia.destino = destino;
            return this;
        }

        public Builder tax(TaxaTransferencia taxa) {
            transferencia.taxa = taxa;
            return this;
        }

        public Builder valor(MonetaryAmount amount) {
            transferencia.valor = amount;
            return this;
        }

        public Builder valor(BigDecimal amount) {
            transferencia.valor = FastMoney.of(amount, MonetaryUtil.BRL);
            return this;
        }

        public Transferencia build() {

            Objects.requireNonNull(transferencia.origem, "Origem não pode ser nula");
            Objects.requireNonNull(transferencia.destino, "Destino não pode ser nula");
            Objects.requireNonNull(transferencia.valor, "Valor não pode ser nula");
            Objects.requireNonNull(transferencia.taxa, "Taxa não pode ser nula");
            return transferencia;
        }

    }

   

}
