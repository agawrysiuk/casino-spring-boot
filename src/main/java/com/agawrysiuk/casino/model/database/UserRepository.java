package com.agawrysiuk.casino.model.database;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    User findByEmail(String email);
    User findByUsername(String username);
}
