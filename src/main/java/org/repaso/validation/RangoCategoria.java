package org.repaso.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RangoCategoriaValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)

public @interface RangoCategoria {
    String message() default "La categoría debe ser 100, 200, ..., 1000.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
