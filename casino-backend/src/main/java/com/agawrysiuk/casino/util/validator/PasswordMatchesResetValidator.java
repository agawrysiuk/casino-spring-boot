package com.agawrysiuk.casino.util.validator;

import com.agawrysiuk.casino.user.dto.PasswordDto;

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
