package br.gbank.gbank.model;

import javax.money.MonetaryAmount;

public class TaxaNormalTransferencia implements TaxaTransferencia {

   @Override
   public MonetaryAmount calcular(MonetaryAmount monetaryAmount) {
       return monetaryAmount.multiply(0.05);
   }

}
