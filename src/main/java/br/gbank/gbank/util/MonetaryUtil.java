package br.gbank.gbank.util;

import java.math.BigDecimal;
import java.util.Optional;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.MonetaryQuery;

import org.javamoney.moneta.FastMoney;

public final class MonetaryUtil {

    private MonetaryUtil() {
        //empty
    }

    public static final CurrencyUnit BRL = Monetary.getCurrency("BRL");

    public static BigDecimal convertToBigDecimal(MonetaryAmount amount) {

        MonetaryQuery<BigDecimal> extract
		= (m) -> m.getNumber()
		.numberValue(BigDecimal.class);

        return Optional.ofNullable(amount)
				.orElse(FastMoney.zero(BRL))
				.query(extract);
    }
    
    public static MonetaryAmount convertToMoney(BigDecimal amount) {
        return FastMoney.of(amount.setScale(2), BRL);
    }
}
