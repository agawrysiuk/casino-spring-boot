package com.agawrysiuk.casino.repo;

import com.agawrysiuk.casino.model.accounts.CasinoUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CasinoUserRepository extends JpaRepository<CasinoUser,Integer> {
    CasinoUser findByNickname(String nickname);
}
