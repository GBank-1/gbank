package br.gbank.gbank.model;

import javax.money.MonetaryAmount;

import org.javamoney.moneta.FastMoney;

import br.gbank.gbank.util.MonetaryUtil;

public class TaxaEspecialTransferencia implements TaxaTransferencia {

   public MonetaryAmount calcular(MonetaryAmount monetaryAmount) {
        return FastMoney.of(0, MonetaryUtil.BRL);
   }

}
