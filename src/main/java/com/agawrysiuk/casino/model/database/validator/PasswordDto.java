package com.agawrysiuk.casino.model.database.validator;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@PasswordMatchesReset
public class PasswordDto {
    private String username;
    @NotNull
    @NotEmpty
    private String oldPassword;
    @NotNull
    @NotEmpty
    @ValidPassword
    private String password;
    private String matchingPassword;
}
