package it.proof.calculator.validation;

import it.proof.calculator.model.Policy;
import it.proof.calculator.model.PolicyObject;
import it.proof.calculator.model.PolicySubObject;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class PolicyValidator {

    public static final String POLICY_CANNOT_BE_NULL = "Policy cannot be null";
    public static final String OBJECTS_CANNOT_BE_NULL = "Objects cannot be null";
    public static final String OBJECTS_MUST_BE_SET = "Objects must be set";
    public static final String SUB_OBJECTS_CANNOT_BE_NULL = "Sub-objects cannot be null";
    public static final String SUB_OBJECTS_MUST_BE_SET = "Sub-objects must be set";
    public static final String SUB_OBJECT_CANNOT_BE_NULL = "Sub-object cannot be null";
    public static final String RISK_TYPE_IN_SUB_OBJECT_CANNOT_BE_NULL = "Risk type in sub-object cannot be null";
    public static final String INSURANCE_SUM_IN_SUB_OBJECT_CANNOT_BE_NULL = "Insurance sum in sub-object cannot be null";
    public static final String INSURANCE_SUM_IN_SUB_OBJECT_HAVE_TO_BE_POSITIVE = "Insurance sum in sub-object have to be positive";

    public void validate(Policy policy) {
        validatePolicy(policy);
        validateObjects(policy.getObjects());
        for (var policyObject : policy.getObjects()) {
            validateSubObjects(policyObject.getSubObjects());
        }
    }

    void validatePolicy(Policy policy) {
        Validate.notNull(policy, POLICY_CANNOT_BE_NULL);
    }

    void validateObjects(List<PolicyObject> policyObjects) {
        Validate.notNull(policyObjects, OBJECTS_CANNOT_BE_NULL);
        Validate.notEmpty(policyObjects, OBJECTS_MUST_BE_SET);
    }

    void validateSubObjects(List<PolicySubObject> subObjects) {
        Validate.notNull(subObjects, SUB_OBJECTS_CANNOT_BE_NULL);
        Validate.notEmpty(subObjects, SUB_OBJECTS_MUST_BE_SET);
        for (var subObject : subObjects) {
            Validate.notNull(subObject, SUB_OBJECT_CANNOT_BE_NULL);
            Validate.notNull(subObject.getRiskType(), RISK_TYPE_IN_SUB_OBJECT_CANNOT_BE_NULL);
            Validate.notNull(subObject.getSum(), INSURANCE_SUM_IN_SUB_OBJECT_CANNOT_BE_NULL);
            Validate.isTrue(subObject.getSum().compareTo(BigDecimal.ZERO) > 0,
                    INSURANCE_SUM_IN_SUB_OBJECT_HAVE_TO_BE_POSITIVE);
        }
    }
}
