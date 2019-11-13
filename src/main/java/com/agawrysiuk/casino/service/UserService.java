package com.agawrysiuk.casino.service;

import com.agawrysiuk.casino.model.database.CasinoUser;

public interface UserService {
    CasinoUser findUsername(String nickname);
    void updateBalance(double balance, String nickname);
}
