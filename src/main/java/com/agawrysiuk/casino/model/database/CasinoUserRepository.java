package com.agawrysiuk.casino.model.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CasinoUserRepository extends JpaRepository<CasinoUser,Integer> {
    CasinoUser findByNickname(String nickname);
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE CasinoUser user SET user.balance = balance where user.nickname = nickname")
    void updateBalance(@Param("balance") double balance, @Param("nickname") String nickname);
}
