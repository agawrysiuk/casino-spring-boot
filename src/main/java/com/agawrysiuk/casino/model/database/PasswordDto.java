package com.agawrysiuk.casino.model.database;

import com.agawrysiuk.casino.model.database.validator.PasswordMatchesReset;
import com.agawrysiuk.casino.model.database.validator.ValidPassword;
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
