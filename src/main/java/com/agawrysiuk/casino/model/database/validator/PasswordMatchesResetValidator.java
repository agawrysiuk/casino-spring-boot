package com.agawrysiuk.casino.model.database.validator;

import com.agawrysiuk.casino.model.database.PasswordDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesResetValidator implements ConstraintValidator<PasswordMatchesReset, Object> {

    @Override
    public void initialize(PasswordMatchesReset constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        PasswordDto passwordDto = (PasswordDto) obj;
        return passwordDto.getPassword().equals(passwordDto.getMatchingPassword());
    }
}
