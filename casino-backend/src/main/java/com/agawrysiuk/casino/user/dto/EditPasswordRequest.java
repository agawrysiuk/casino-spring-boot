package com.agawrysiuk.casino.user.dto;

import com.agawrysiuk.casino.util.validator.PasswordMatchesReset;
import com.agawrysiuk.casino.util.validator.ValidPassword;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@PasswordMatchesReset
public class EditPasswordRequest {
    @NotBlank
    private String oldPassword;
    @NotBlank
    @ValidPassword
    private String password;
    private String matchingPassword;
}
