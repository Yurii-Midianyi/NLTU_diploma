package com.nltu.app.diplomaproject.annotations;

import com.nltu.app.diplomaproject.exceptions.ExceptionMessage;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = MaxSizeValidator.class)
public @interface ListMaxSize {
    String message() default ExceptionMessage.LIST_SIZE_FAIL;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int max() default 10;
}
