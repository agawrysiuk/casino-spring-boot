package com.agawrysiuk.casino.util.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = CreditCardValidator.class)
@Documented
public @interface ValidCreditCard {
    String message() default "Invalid credit card";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
