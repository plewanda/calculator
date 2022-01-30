package it.proof.calculator;

import it.proof.calculator.formula.FireFormula;
import it.proof.calculator.validation.PolicyValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.util.List;

import static it.proof.calculator.TestCommons.createPolicy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.MockitoAnnotations.openMocks;

public class PremiumCalculatorTest {

    @Mock
    PolicyValidator validator;

    @Mock
    FormulasRegistry registry;

    @InjectMocks
    PremiumCalculator sut;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    public void calculate_whenRegistryHasOnlyOneFormulaType_shouldCalculateForOnlyThatType() {
        //given
        given(registry.getPremiumFormulas()).willReturn(List.of(new FireFormula()));
        doNothing().when(validator).validate(any());

        var policy = createPolicy(List.of(10.0), List.of(10.0));

        //when
        var result = sut.calculate(policy);

        //then
        assertEquals(result.compareTo(FireFormula.COEFFICIENT_LO.multiply(BigDecimal.TEN)), 0);
    }

}