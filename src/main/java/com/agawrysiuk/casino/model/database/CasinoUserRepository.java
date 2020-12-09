package com.agawrysiuk.casino.model.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CasinoUserRepository extends JpaRepository<CasinoUser,Integer> {
    CasinoUser findByNickname(String nickname);

    CasinoUser save(CasinoUser casinoUser);
}
