package br.gbank.gbank.model;

import javax.money.MonetaryAmount;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.javamoney.moneta.FastMoney;

import br.gbank.gbank.model.convert.MonetaryAmountConverter;
import br.gbank.gbank.util.MonetaryUtil;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @EqualsAndHashCode.Include
    private Long numero;

    @OneToOne
    private Cliente cliente;
    
    @Convert(converter = MonetaryAmountConverter.class) 
    private MonetaryAmount saldo;

    private boolean ativa;

    /**
     * @param numero
     */
    public Conta(Long numero) {
        this.numero = numero;
        this.ativa = true;
        this.saldo = FastMoney.of(100000, MonetaryUtil.BRL); 
    }   

}
