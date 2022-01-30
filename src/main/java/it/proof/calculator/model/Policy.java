package it.proof.calculator.model;

import it.proof.calculator.model.enums.PolicyStatus;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Policy {
    String name;
    PolicyStatus status;
    List<PolicyObject> objects;
}
