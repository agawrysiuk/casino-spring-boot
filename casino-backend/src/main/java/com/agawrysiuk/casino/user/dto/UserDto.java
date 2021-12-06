package com.agawrysiuk.casino.user.dto;

import com.agawrysiuk.casino.util.validator.PasswordMatches;
import com.agawrysiuk.casino.util.validator.ValidEmail;
import com.agawrysiuk.casino.util.validator.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@PasswordMatches
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotBlank
    private String username;

    @NotBlank
    @ValidPassword
    private String password;
    private String matchingPassword;

    @NotBlank
    @ValidEmail
    private String email;
}
