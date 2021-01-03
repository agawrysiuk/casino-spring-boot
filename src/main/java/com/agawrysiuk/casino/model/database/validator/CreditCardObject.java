package com.agawrysiuk.casino.model.database.validator;

import com.agawrysiuk.casino.model.database.validator.ValidCreditCard;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

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
    private String ccv;
    @NotNull
    private BigDecimal depositAmount;
}
