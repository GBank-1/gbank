package br.gbank.gbank.model;

import javax.money.MonetaryAmount;

public interface TaxaTransferencia {

   MonetaryAmount calcular(MonetaryAmount monetaryAmount);

}
