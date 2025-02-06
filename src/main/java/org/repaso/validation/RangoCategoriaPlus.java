package org.repaso.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RangoCategoriaPlusValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)

public @interface RangoCategoriaPlus {
    int[] value();

    String message() default "{err.c.list}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
