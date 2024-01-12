package com.nltu.app.diplomaproject.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;

public class MaxSizeValidator implements ConstraintValidator<ListMaxSize, List<?>> {
    private int max;

    @Override
    public void initialize(ListMaxSize constraintAnnotation) {
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(List<?> objects, ConstraintValidatorContext constraintValidatorContext) {
        return objects.size()<=max;
    }
}
