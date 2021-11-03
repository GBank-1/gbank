package br.gbank.gbank.model.convert;

import java.math.BigDecimal;

import javax.money.MonetaryAmount;
import javax.persistence.AttributeConverter;

import br.gbank.gbank.util.MonetaryUtil;

public class MonetaryAmountConverter implements AttributeConverter<MonetaryAmount, BigDecimal> {
	
    @Override
    public BigDecimal convertToDatabaseColumn(MonetaryAmount attribute) {
        return MonetaryUtil.convertToBigDecimal(attribute);
    }

    @Override
    public MonetaryAmount convertToEntityAttribute(BigDecimal dbData) {
        return MonetaryUtil.convertToMoney(dbData);
    }

}
