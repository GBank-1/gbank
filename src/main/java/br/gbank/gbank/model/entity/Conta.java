package br.gbank.gbank.model.entity;

import java.time.LocalDate;
import java.util.Objects;

import javax.money.MonetaryAmount;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.javamoney.moneta.FastMoney;

import br.gbank.gbank.exception.ContaSemSaldoException;
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
    @Column(unique = true)
    private Long numero;

    @OneToOne
    private Cliente cliente;

    @Convert(converter = MonetaryAmountConverter.class)
    private MonetaryAmount saldo;

    private boolean ativa;

    public Conta() {
        // empty
    }

    public Conta(Cliente cliente) {
        super();
        this.cliente = cliente;
        this.ativa = true;
        this.saldo = FastMoney.of(100000, MonetaryUtil.BRL);
    }

    /**
     * @param numero
     */
    public Conta(Cliente cliente, Long numero) {
        this(cliente);
        this.numero = numero;
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

    public boolean permiteDebitar(MonetaryAmount amount) {
        return saldo.compareTo(amount) > 0;
    }

    public void debitar(MonetaryAmount amount) throws ContaSemSaldoException {
        if (!permiteDebitar(amount)) {
            throw new ContaSemSaldoException();
        }
        saldo = saldo.subtract(amount);
    }

    public void creditar(MonetaryAmount amount) {
        saldo = saldo.add(amount);
    }

    public LocalDate getDataCadastrada() {
        Objects.requireNonNull(cliente);
        return cliente.getDataCadastro();
    }

}
