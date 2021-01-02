package com.agawrysiuk.casino.model.database.validator;

import com.agawrysiuk.casino.model.database.validator.ValidCreditCard;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ValidCreditCard
public class CreditCardObject {
    @NotNull
    @NotEmpty
    private String firstName;
    @NotNull
    @NotEmpty
    private String surname;
    @NotNull
    @NotEmpty
    private String cardNumber;
    @NotNull
    @NotEmpty
    private String expiryDate;
    @NotNull
    @NotEmpty
    private String CCV;
    @NotNull
    @NotEmpty
    private String depositAmount;
}
