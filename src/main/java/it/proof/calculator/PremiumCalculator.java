package it.proof.calculator;

import it.proof.calculator.model.Policy;
import it.proof.calculator.validation.PolicyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class PremiumCalculator {

    private PolicyValidator validator;

    private FormulasRegistry formulasRegistry;

    @Autowired
    public PremiumCalculator(FormulasRegistry formulasRegistry, PolicyValidator validator) {
        this.formulasRegistry = formulasRegistry;
        this.validator = validator;
    }

    public BigDecimal calculate(Policy policy) {
        validator.validate(policy);

        var policySum = formulasRegistry.getPremiumFormulas().stream()
                .map(formula -> formula.calculatePremium(policy))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return roundToCents(policySum);
    }

    private BigDecimal roundToCents(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_UP);
    }
}
