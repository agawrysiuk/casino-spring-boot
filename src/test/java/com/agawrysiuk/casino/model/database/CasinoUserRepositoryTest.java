package com.agawrysiuk.casino.model.database;

import com.agawrysiuk.casino.casinouser.CasinoUser;
import com.agawrysiuk.casino.casinouser.CasinoUserRepository;
import com.agawrysiuk.casino.user.User;
import com.agawrysiuk.casino.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public class CasinoUserRepositoryTest {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private CasinoUserRepository casinoUserRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void findCasinoUserByName() {
        CasinoUser casinoUser = new CasinoUser();
        casinoUser.setNickname("admin");
        entityManager.persist(casinoUser);
        entityManager.flush();

        CasinoUser found = casinoUserRepository.findByNickname(casinoUser.getNickname());

        assertThat(found.getNickname())
                .isEqualTo(casinoUser.getNickname());
    }

    @Test
    public void findUSerByName() {
        User user = new User();
        user.setUsername("admin");
        entityManager.persist(user);
        entityManager.flush();

        User found = userRepository.findByUsername(user.getUsername()).get();

        assertThat(found.getUsername())
                .isEqualTo(user.getUsername());
    }
}