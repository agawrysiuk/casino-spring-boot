package com.agawrysiuk.casino.model.database;

import com.agawrysiuk.casino.model.database.validator.PasswordMatches;
import com.agawrysiuk.casino.model.database.validator.ValidEmail;
import com.agawrysiuk.casino.model.database.validator.ValidPassword;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@PasswordMatches
public class UserDto {
    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    @ValidPassword
    private String password;
    private String matchingPassword;

    @ValidEmail
    @NotNull
    @NotEmpty
    private String email;
}
