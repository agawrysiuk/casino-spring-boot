package com.agawrysiuk.casino.service;

import com.agawrysiuk.casino.model.database.CasinoUser;
import com.agawrysiuk.casino.model.database.User;
import com.agawrysiuk.casino.model.database.UserDto;

public interface UserService {
    User findUserByEmail(String email);
    User findUserByUsername(String username);
    User registerNewUserAccount(UserDto accountDto);

    CasinoUser findCasinoUserByUsername(String nickname);
    void updateCasinoUserBalance(double balance, String nickname);
}
