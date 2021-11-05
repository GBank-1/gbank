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
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Entity
@Table(schema = "IBM")
public class Conta {


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

    public Conta() {
        super();
        this.ativa = true;
        this.saldo = FastMoney.of(100000, MonetaryUtil.BRL); 
    }

    /**
     * @param numero
     */
   
    public Conta(Long numero) {
        this()
        this.numero=numero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public MonetaryAmount getSaldo() {
        return saldo;
    }

    public void setSaldo(MonetaryAmount saldo) {
        this.saldo = saldo;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

}
