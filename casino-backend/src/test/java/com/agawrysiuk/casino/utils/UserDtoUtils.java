package com.agawrysiuk.casino.utils;

import com.agawrysiuk.casino.user.dto.UserDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserDtoUtils {

    public UserDto correctUser() {
        return UserDto.builder()
                .username("Dawid")
                .email("dawid@dawid.pl")
                .password("S0me_GOOD$password")
                .matchingPassword("S0me_GOOD$password")
                .build();
    }

    public UserDto withUsername(String username) {
        UserDto userDto = correctUser();
        userDto.setUsername(username);
        return userDto;
    }
}
