package br.gbank.gbank.model.entity;

import java.time.LocalDateTime;

import javax.money.MonetaryAmount;
import javax.persistence.*;

import br.gbank.gbank.model.Transferencia;
import br.gbank.gbank.model.convert.MonetaryAmountConverter;

@Entity
@Table(schema = "IBM")
public class HistoricoTransferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Conta origem;

    @ManyToOne
    private Conta destino;

    @Convert(converter = MonetaryAmountConverter.class)
    private MonetaryAmount valor;

    private boolean realizada;

    private LocalDateTime dataHoraSolicitada;

    private LocalDateTime dataHoraEfetuada;

    public HistoricoTransferencia() {
        //empty
    }

    public HistoricoTransferencia(Transferencia transferencia) {
        dataHoraSolicitada = transferencia.getDataHoraSolicitada();
        destino = transferencia.getDestino();
        origem = transferencia.getOrigem();
        valor = transferencia.getValor();
        dataHoraEfetuada = LocalDateTime.now();
        realizada = Boolean.TRUE;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Conta getOrigem() {
        return origem;
    }

    public void setOrigem(Conta origem) {
        this.origem = origem;
    }

    public Conta getDestino() {
        return destino;
    }

    public void setDestino(Conta destino) {
        this.destino = destino;
    }

    public MonetaryAmount getValor() {
        return valor;
    }

    public void setValor(MonetaryAmount valor) {
        this.valor = valor;
    }

    public boolean isRealizada() {
        return realizada;
    }

    public void setRealizada(boolean realizada) {
        this.realizada = realizada;
    }

    public LocalDateTime getDataHoraSolicitada() {
        return dataHoraSolicitada;
    }

    public void setDataHoraSolicitada(LocalDateTime dataHoraSolicitada) {
        this.dataHoraSolicitada = dataHoraSolicitada;
    }

    public LocalDateTime getDataHoraEfetuada() {
        return dataHoraEfetuada;
    }

    public void setDataHoraEfetuada(LocalDateTime dataHoraEfetuada) {
        this.dataHoraEfetuada = dataHoraEfetuada;
    }
}
