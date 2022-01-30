package it.proof.calculator.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class PolicyObject {
    String name;
    List<PolicySubObject> subObjects;
}
