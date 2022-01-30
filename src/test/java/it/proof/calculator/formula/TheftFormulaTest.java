package it.proof.calculator.formula;

import it.proof.calculator.model.enums.RiskType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static it.proof.calculator.TestCommons.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TheftFormulaTest {

    TheftFormula sut = new TheftFormula();

    @Test
    public void calculatePremium_whenInFirstSection_shouldUseCoefficientLo() {
        //given
        var policy = createSingleTypePolicy(14.99, RiskType.THEFT);

        //when
        var result = sut.calculatePremium(policy);

        //then
        assertEquals(TheftFormula.COEFFICIENT_LO.multiply(BigDecimal.valueOf(14.99)).compareTo(result), 0);
    }

    @Test
    public void calculatePremium_whenInSecondSection_shouldUseCoefficientHi() {
        //given
        var policy = createSingleTypePolicy(15.0, RiskType.THEFT);

        //when
        var result = sut.calculatePremium(policy);

        //then
        assertEquals(TheftFormula.COEFFICIENT_HI.multiply(BigDecimal.valueOf(15)).compareTo(result), 0);
    }

    @Test
    public void calculatePremium_whenSumZero_shouldReturnZero() {
        //given
        var policy = createSingleTypePolicy(0.0, RiskType.THEFT);

        //when
        var result = sut.calculatePremium(policy);

        //then
        assertEquals(result.compareTo(BigDecimal.ZERO), 0);
    }

    @Test
    public void calculatePremium_whenOnlyFireSubObjects_shouldReturnZero() {
        //given
        var policy = createSingleTypePolicy(100.0, RiskType.FIRE);

        //when
        var result = sut.calculatePremium(policy);

        //then
        assertEquals(result.compareTo(BigDecimal.ZERO), 0);
    }

    @Test
    public void getSumForType_whenOnlyTheftSubObjects_shouldSumCorrectly() {
        //given
        var policy = createPolicy(List.of(), List.of(10.0, 20.0));

        //when
        var result = sut.getSumForType(policy);

        //then
        assertEquals(result.compareTo(BigDecimal.valueOf(30.0)), 0);
    }

    @Test
    public void getSumForType_whenFireAndTheftSubObjects_shouldSumCorrectly() {
        //given
        var policy = createPolicy(List.of(50.0, 100.0), List.of(10.0, 20.0));

        //when
        var result = sut.getSumForType(policy);

        //then
        assertEquals(result.compareTo(BigDecimal.valueOf(30.0)), 0);
    }
}