package it.proof.calculator.validation;

import it.proof.calculator.model.PolicySubObject;
import it.proof.calculator.model.enums.RiskType;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static it.proof.calculator.validation.PolicyValidator.*;
import static org.junit.jupiter.api.Assertions.*;

public class PolicyValidatorTest {

    PolicyValidator sut = new PolicyValidator();

    @Test(expected = NullPointerException.class)
    public void validatePolicy_whenPolicyNull_shouldThrowException(){
        //given

        //when
        try {
            sut.validatePolicy(null);
        }
        catch (Exception ex) {
            //then
            assertEquals(POLICY_CANNOT_BE_NULL, ex.getMessage());
            throw ex;
        }
        fail("Should throw exception");
    }

    @Test(expected = NullPointerException.class)
    public void validateObjects_whenObjectsNull_shouldThrowException(){
        //given

        //when
        try {
            sut.validateObjects(null);
        }
        catch (Exception ex) {
            //then
            assertEquals(OBJECTS_CANNOT_BE_NULL, ex.getMessage());
            throw ex;
        }
        fail("Should throw exception");
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateObjects_whenObjectsEmpty_shouldThrowException(){
        //given

        //when
        try {
            sut.validateObjects(Collections.emptyList());
        }
        catch (Exception ex) {
            //then
            assertEquals(OBJECTS_MUST_BE_SET, ex.getMessage());
            throw ex;
        }
        fail("Should throw exception");
    }

    @Test(expected = NullPointerException.class)
    public void validateSubObjects_whenSubObjectsNull_shouldThrowException(){
        //given

        //when
        try {
            sut.validateSubObjects(null);
        }
        catch (Exception ex) {
            //then
            assertEquals(SUB_OBJECTS_CANNOT_BE_NULL, ex.getMessage());
            throw ex;
        }
        fail("Should throw exception");
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateSubObjects_whenSubObjectsEmpty_shouldThrowException(){
        //given

        //when
        try {
            sut.validateSubObjects(Collections.emptyList());
        }
        catch (Exception ex) {
            //then
            assertEquals(SUB_OBJECTS_MUST_BE_SET, ex.getMessage());
            throw ex;
        }
        fail("Should throw exception");
    }

    @Test(expected = NullPointerException.class)
    public void validateSubObjects_whenSubObjectNull_shouldThrowException(){
        //given
        var subObjects = new ArrayList<PolicySubObject>();
        subObjects.add(null);

        //when
        try {
            sut.validateSubObjects(subObjects);
        }
        catch (Exception ex) {
            //then
            assertEquals(SUB_OBJECT_CANNOT_BE_NULL, ex.getMessage());
            throw ex;
        }
        fail("Should throw exception");
    }

    @Test(expected = NullPointerException.class)
    public void validateSubObjects_whenRiskTypeNull_shouldThrowException(){
        //given
        var subObject = PolicySubObject
                .builder()
                .riskType(null)
                .sum(BigDecimal.ONE)
                .build();

        //when
        try {
            sut.validateSubObjects(List.of(subObject));
        }
        catch (Exception ex) {
            //then
            assertEquals(RISK_TYPE_IN_SUB_OBJECT_CANNOT_BE_NULL, ex.getMessage());
            throw ex;
        }
        fail("Should throw exception");
    }

    @Test(expected = NullPointerException.class)
    public void validateSubObjects_whenSumNull_shouldThrowException(){
        //given
        var subObject = PolicySubObject
                .builder()
                .riskType(RiskType.THEFT)
                .sum(null)
                .build();

        //when
        try {
            sut.validateSubObjects(List.of(subObject));
        }
        catch (Exception ex) {
            //then
            assertEquals(INSURANCE_SUM_IN_SUB_OBJECT_CANNOT_BE_NULL, ex.getMessage());
            throw ex;
        }
        fail("Should throw exception");
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateSubObjects_whenSumNotPositive_shouldThrowException(){
        //given
        var subObject = PolicySubObject
                .builder()
                .riskType(RiskType.THEFT)
                .sum(BigDecimal.ZERO)
                .build();

        //when
        try {
            sut.validateSubObjects(List.of(subObject));
        }
        catch (Exception ex) {
            //then
            assertEquals(INSURANCE_SUM_IN_SUB_OBJECT_HAVE_TO_BE_POSITIVE, ex.getMessage());
            throw ex;
        }
        fail("Should throw exception");
    }
}