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
@Constraint(validatedBy = NotEmptyElementsValidator.class)
public @interface NotEmptyAnswers {
    String message() default ExceptionMessage.ANSWER_VALIDATION_FAIL;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
