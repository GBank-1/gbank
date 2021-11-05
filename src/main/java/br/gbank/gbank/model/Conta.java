package br.gbank.gbank.model;

import javax.money.MonetaryAmount;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.javamoney.moneta.FastMoney;

import br.gbank.gbank.model.convert.MonetaryAmountConverter;
import br.gbank.gbank.util.MonetaryUtil;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(schema = "IBM")
public class Conta {

    public Conta() {
        super();
        this.ativa = true;
        this.saldo = FastMoney.of(100000, MonetaryUtil.BRL); 
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Include
    private Long numero;

    @OneToOne
    private Cliente cliente;
    
    @Convert(converter = MonetaryAmountConverter.class) 
    private MonetaryAmount saldo;

    private boolean ativa;

}
