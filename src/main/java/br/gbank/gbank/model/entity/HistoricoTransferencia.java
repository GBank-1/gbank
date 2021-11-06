package br.gbank.gbank.model.entity;

import java.time.LocalDateTime;

import javax.money.MonetaryAmount;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.gbank.gbank.model.Transferencia;
import br.gbank.gbank.model.convert.MonetaryAmountConverter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class HistoricoTransferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @EqualsAndHashCode.Include
    @ManyToOne
    private Conta origem;

    @EqualsAndHashCode.Include
    @ManyToOne
    private Conta destino;

    @Convert(converter = MonetaryAmountConverter.class)
    private MonetaryAmount valor;

    @EqualsAndHashCode.Include
    private LocalDateTime data;

    private boolean realizada;

    private LocalDateTime dataHoraSolicitada;

    private LocalDateTime dataHoraEfetuada;

    public HistoricoTransferencia(Transferencia transferencia) {
        dataHoraSolicitada = transferencia.getDataHoraSolicitada();
        destino = transferencia.getDestino();
        origem = transferencia.getOrigem();
        valor = transferencia.getValor();
        dataHoraEfetuada = LocalDateTime.now();
        realizada = Boolean.TRUE;
    }

}
