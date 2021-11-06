package br.gbank.gbank.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;

import br.gbank.gbank.exception.MoedaNaoSuportadaException;
import br.gbank.gbank.util.MonetaryUtil;

public class ValorMonetarioVO implements Serializable {

    private BigDecimal valor;
    private String moeda;
    
    public ValorMonetarioVO() {
    }

    public ValorMonetarioVO(BigDecimal valor, CurrencyUnit brl) {
        this.valor = valor;
        this.moeda = brl.getCurrencyCode();
    }

    public static ValorMonetarioVO fromMonetaryAmount(MonetaryAmount amount) {
        ValorMonetarioVO vo = new ValorMonetarioVO();
        vo.valor = MonetaryUtil.convertToBigDecimal(amount);
        vo.moeda = MonetaryUtil.BRL.getCurrencyCode();
        return vo;
    }

    public MonetaryAmount toMonetaryAmount() {
        if (MonetaryUtil.BRL.getCurrencyCode().equals(this.moeda)) {
            throw new MoedaNaoSuportadaException();
        }
        return MonetaryUtil.convertToMoney(this.valor);
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getMoeda() {
        return moeda;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

}
