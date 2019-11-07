package com.agawrysiuk.casino.repo;

import com.agawrysiuk.casino.model.accounts.CasinoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CasinoUserRepository extends JpaRepository<CasinoUser,Integer> {

}
