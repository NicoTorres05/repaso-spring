package org.repaso.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

public class RangoCategoriaValidator implements ConstraintValidator<RangoCategoria, Integer> {
    private static final Set<Integer> values = Set.of(100, 200, 300, 400, 500, 600, 700, 800, 900, 1000);
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value != null && values.contains(value);
    }
}
