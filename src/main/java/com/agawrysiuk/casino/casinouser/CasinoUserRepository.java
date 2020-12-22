package com.agawrysiuk.casino.casinouser;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CasinoUserRepository extends JpaRepository<CasinoUser,Integer> {
    CasinoUser findByNickname(String nickname);

    CasinoUser save(CasinoUser casinoUser);
}
