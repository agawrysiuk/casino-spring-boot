package com.agawrysiuk.casino.service;

import com.agawrysiuk.casino.model.database.CasinoUser;
import com.agawrysiuk.casino.model.database.User;
import com.agawrysiuk.casino.model.database.UserDto;

public interface UserService {
    User findByEmail(String email);
    User findByConfirmationToken(String confirmationToken);
    void saveUser(User user);
    User registerNewUserAccount(UserDto accountDto);

    CasinoUser findUsername(String nickname);
    void updateBalance(double balance, String nickname);
}
