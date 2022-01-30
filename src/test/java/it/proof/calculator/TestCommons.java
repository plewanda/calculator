package it.proof.calculator;

import it.proof.calculator.model.Policy;
import it.proof.calculator.model.PolicyObject;
import it.proof.calculator.model.PolicySubObject;
import it.proof.calculator.model.enums.PolicyStatus;
import it.proof.calculator.model.enums.RiskType;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class TestCommons {

    public static Policy createPolicy(List<Double> fireSum, List<Double> theftSum) {
        var subObjects = fireSum.stream()
                .map(sum -> createPolicySubObject(sum, RiskType.FIRE))
                .collect(Collectors.toList());

        var theftSubObjects = theftSum.stream()
                .map(sum -> createPolicySubObject(sum, RiskType.THEFT))
                .toList();

        subObjects.addAll(theftSubObjects);

        var object = createPolicyObject(subObjects);

        return createPolicy(List.of(object));
    }

    public static Policy createSingleTypePolicy(double sum, RiskType riskType) {
        var subObject = createPolicySubObject(sum, riskType);

        var object = createPolicyObject(List.of(subObject));

        return createPolicy(List.of(object));
    }

    public static Policy createPolicy(List<PolicyObject> objects) {
        return Policy.builder()
                .name("Policy")
                .status(PolicyStatus.APPROVED)
                .objects(objects)
                .build();
    }

    public static PolicyObject createPolicyObject(List<PolicySubObject> subObjects) {
        return PolicyObject.builder()
                .name("Object")
                .subObjects(subObjects)
                .build();
    }

    public static PolicySubObject createPolicySubObject(double sum, RiskType riskType) {
        return PolicySubObject.builder()
                .name("Sub-object")
                .sum(BigDecimal.valueOf(sum))
                .riskType(riskType).build();
    }
}
