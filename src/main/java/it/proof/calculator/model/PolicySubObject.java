package it.proof.calculator.model;

import it.proof.calculator.model.enums.RiskType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class PolicySubObject {
    String name;
    BigDecimal sum;
    RiskType riskType;
}
