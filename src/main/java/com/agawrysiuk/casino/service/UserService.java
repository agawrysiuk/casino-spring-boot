package com.agawrysiuk.casino.service;

import com.agawrysiuk.casino.model.database.CasinoUser;
import com.agawrysiuk.casino.model.database.User;
import com.agawrysiuk.casino.model.database.UserDto;

public interface UserService {
    User findByEmail(String email);
    User findByUsername(String username);
    User registerNewUserAccount(UserDto accountDto);

    CasinoUser findUsername(String nickname);
    void updateBalance(double balance, String nickname);
}
