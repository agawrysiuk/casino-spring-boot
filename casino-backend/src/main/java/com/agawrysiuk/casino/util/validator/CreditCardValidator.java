package com.agawrysiuk.casino.util.validator;

import com.agawrysiuk.casino.user.dto.CreditCardObjectDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class CreditCardValidator implements ConstraintValidator<ValidCreditCard, Object> {

    @Override
    public void initialize(ValidCreditCard constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        CreditCardObjectDto creditCard = (CreditCardObjectDto) obj;
        //here we can add some more validations,
        //but for now, we will only check expiry date
        //since everything else is dynamically checked by javascript
        //or by annotations inside the CreditCardObject class
        int expiryMonth = Integer.parseInt(
                creditCard.getExpiryDate().split("-")[0]);
        int expiryYear = Integer.parseInt(
                creditCard.getExpiryDate().split("-")[1]);

        LocalDate date = LocalDate.now();

        if ((date.getYear() - 2000) > expiryYear) {
            if (date.getYear() - 2000 == expiryYear && date.getMonthValue() > expiryMonth) {
                return false;
            }
        }

        return true;
    }
}
