package it.proof.calculator.formula;

import it.proof.calculator.model.Policy;
import it.proof.calculator.model.enums.RiskType;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class FireFormula implements PremiumFormula {

    RiskType type = RiskType.FIRE;

    public static final BigDecimal COEFFICIENT_LO = BigDecimal.valueOf(0.014);
    public static final BigDecimal COEFFICIENT_HI = BigDecimal.valueOf(0.024);
    public static final BigDecimal LIMIT = BigDecimal.valueOf(100);

    public BigDecimal calculatePremium(Policy policy) {
        var sum = getSumForType(policy);
        return sum.multiply(sum.compareTo(LIMIT) > 0 ? COEFFICIENT_HI : COEFFICIENT_LO);
    }
}
