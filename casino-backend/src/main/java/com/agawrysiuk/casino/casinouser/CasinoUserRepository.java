package com.agawrysiuk.casino.casinouser;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CasinoUserRepository extends JpaRepository<CasinoUser,Integer> {
    Optional<CasinoUser> findByNickname(String nickname);

    CasinoUser save(CasinoUser casinoUser);
}
