package it.proof.calculator.formula;

import it.proof.calculator.model.Policy;
import it.proof.calculator.model.enums.RiskType;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class TheftFormula implements PremiumFormula {

    RiskType type = RiskType.THEFT;

    public static final BigDecimal COEFFICIENT_LO = BigDecimal.valueOf(0.11);
    public static final BigDecimal COEFFICIENT_HI = BigDecimal.valueOf(0.05);
    public static final BigDecimal LIMIT = BigDecimal.valueOf(15);

    public BigDecimal calculatePremium(Policy policy) {
        var sum = getSumForType(policy);
        return sum.multiply(sum.compareTo(LIMIT) >= 0 ? COEFFICIENT_HI : COEFFICIENT_LO);
    }
}
