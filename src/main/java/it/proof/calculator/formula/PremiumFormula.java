package it.proof.calculator.formula;

import it.proof.calculator.model.Policy;
import it.proof.calculator.model.PolicySubObject;
import it.proof.calculator.model.enums.RiskType;

import java.math.BigDecimal;

public interface PremiumFormula {

    BigDecimal calculatePremium(Policy policy);

    default BigDecimal getSumForType(Policy policy) {
        return policy.getObjects().stream()
                .flatMap(obj -> obj.getSubObjects().stream())
                .filter(subObject -> subObject.getRiskType().equals(getType()))
                .map(PolicySubObject::getSum)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    RiskType getType();
}
