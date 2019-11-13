package com.agawrysiuk.casino.service;

import com.agawrysiuk.casino.model.database.CasinoUser;
import com.agawrysiuk.casino.model.database.CasinoUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final CasinoUserRepository casinoUserRepository;

    @Autowired
    public UserServiceImpl(CasinoUserRepository casinoUserRepository) {
        this.casinoUserRepository = casinoUserRepository;
    }

    public CasinoUser findUsername(String nickname) {
        return casinoUserRepository.findByNickname(nickname);
    }

    public void updateBalance(double balance, String nickname) {
        casinoUserRepository.updateBalance(balance, nickname);
    }
}
