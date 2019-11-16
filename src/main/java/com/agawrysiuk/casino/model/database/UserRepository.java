package com.agawrysiuk.casino.model.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User,String> {
    User findByEmail(String email);
    User findByUsername(String username);
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE User user SET user.password = password where user.username = username")
    void changePassword(@Param("password") String password, @Param("username") String username);
}
