package com.agawrysiuk.casino.user.dto;

import com.agawrysiuk.casino.util.validator.PasswordMatchesReset;
import com.agawrysiuk.casino.util.validator.ValidPassword;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@PasswordMatchesReset
public class EditPasswordRequest {
    @NotNull
    @NotEmpty
    private String oldPassword;
    @NotNull
    @NotEmpty
    @ValidPassword
    private String password;
    private String matchingPassword;
}
