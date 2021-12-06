package com.agawrysiuk.casino.user.dto;

import com.agawrysiuk.casino.util.validator.ValidCreditCard;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@ValidCreditCard
public class CreditCardObjectDto {
    @NotBlank
    private String firstName;
    @NotBlank
    private String surname;
    @NotBlank
    @CreditCardNumber
    private String cardNumber;
    @NotBlank
    private String expiryDate;
    @NotBlank
    private String ccv;
    @Positive
    private BigDecimal depositAmount;
}
