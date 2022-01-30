package it.proof.calculator;

import it.proof.calculator.formula.FireFormula;
import it.proof.calculator.formula.PremiumFormula;
import it.proof.calculator.formula.TheftFormula;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Component
public class FormulasRegistry {
    List<PremiumFormula> premiumFormulas = new ArrayList<>();

    public FormulasRegistry() {
        premiumFormulas.add(new FireFormula());
        premiumFormulas.add(new TheftFormula());
    }
}
