package com.agawrysiuk.casino.model.database;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CasinoUserRepository extends JpaRepository<CasinoUser,Integer> {
    CasinoUser findByNickname(String nickname);
}
