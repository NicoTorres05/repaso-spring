package org.repaso.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class RangoCategoriaPlusValidator implements ConstraintValidator<RangoCategoriaPlus, Integer> {

    private int[] valoresPermitidos;

    @Override
    public void initialize(final RangoCategoriaPlus constraintAnnotation) {
        valoresPermitidos = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return Arrays.stream(valoresPermitidos).anyMatch(v -> v == value);
    }
}
