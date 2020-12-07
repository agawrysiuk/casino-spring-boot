package com.agawrysiuk.casino.service;

import com.agawrysiuk.casino.model.database.CasinoUser;
import com.agawrysiuk.casino.model.database.PasswordDto;
import com.agawrysiuk.casino.model.database.User;
import com.agawrysiuk.casino.model.database.UserDto;

public interface UserService {
    User findUserByEmail(String email);
    User findUserByUsername(String username);
    User registerNewUserAccount(UserDto accountDto, boolean admin);
    void changePassword(PasswordDto user);

    CasinoUser findCasinoUserByUsername(String nickname);
    void updateCasinoUserBalance(double balance, String nickname);
    void updateCasinoUserInformation(CasinoUser casinoUser);
    void depositToCasinoUser(double balance, String nickname);
    boolean isEnoughMoney(String nickname, double minNeeded);
    boolean doPasswordsMatch(String oldPassword,String userName);
}
