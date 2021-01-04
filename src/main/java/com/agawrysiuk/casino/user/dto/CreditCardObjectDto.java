package com.agawrysiuk.casino.user.dto;

import com.agawrysiuk.casino.util.validator.ValidCreditCard;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@ValidCreditCard
public class CreditCardObjectDto {
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
