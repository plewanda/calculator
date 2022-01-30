package it.proof.calculator;

import it.proof.calculator.validation.PolicyValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static it.proof.calculator.TestCommons.createPolicy;

public class AcceptanceTests {

    PremiumCalculator calculator;

    @BeforeEach
    public void setUp() {
        var formulasRegistry = new FormulasRegistry();
        var validator = new PolicyValidator();
        calculator = new PremiumCalculator(formulasRegistry, validator);
    }

    /**
     * If there is one policy, one object and two sub-objects as described below, then calculator should return
     * premium = 2.28 EUR
     * Risk type = FIRE, Sum insured = 100.00
     * Risk type = THEFT, Sum insured = 8.00
     */
    @Test
    public void acceptance_test1() {
        //given
        var policy = createPolicy(List.of(100.0), List.of(8.0));

        //when
        var result = calculator.calculate(policy);

        //then
        Assertions.assertEquals(BigDecimal.valueOf(2.28), result);
    }

    /**
     * If policy's total sum insured for risk type FIRE and total sum insured for risk type THEFT are as
     * described below, then calculator should return premium = 17.13 EUR
     * Risk type = FIRE, Sum insured = 500.00
     * Risk type = THEFT, Sum insured = 102.51
     */
    @Test
    public void acceptance_test2() {
        //given
        var policy = createPolicy(List.of(500.0), List.of(102.51));

        //when
        var result = calculator.calculate(policy);

        //then
        Assertions.assertEquals(BigDecimal.valueOf(17.13), result);
    }
}
