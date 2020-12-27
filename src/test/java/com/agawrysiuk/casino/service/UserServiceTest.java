package com.agawrysiuk.casino.service;

import com.agawrysiuk.casino.casinouser.CasinoUser;
import com.agawrysiuk.casino.casinouser.CasinoUserRepository;
import com.agawrysiuk.casino.user.User;
import com.agawrysiuk.casino.user.UserRepository;
import com.agawrysiuk.casino.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {

    @MockBean
    private CasinoUserRepository casinoUserRepository;
    @MockBean
    private UserRepository userRepository;
    @Autowired(required = true)
    private UserService userService;

    private final String name = "admin";
    private final double balance = 100.00;
    private final String mail = "admin@mail.com";
    private final String password = new BCryptPasswordEncoder().encode("myPassword569");
    private final String authority = "ROLE_ADMIN";
    private final boolean enabled = true;

    @BeforeEach
    public void setUp() {
        log.info("setUp() started");
        CasinoUser casinoUser = new CasinoUser();
        casinoUser.setNickname(name);
        casinoUser.setBalance(balance);
        Mockito.when(casinoUserRepository.findByNickname(name))
                .thenReturn(casinoUser);

        User user = new User();
        user.setUsername(name);
        user.setEmail(mail);
        user.setPassword(password);
        user.setEnabled(enabled);
        user.setAuthority(authority);
        Mockito.when(userService.findUserByUsername(name))
                .thenReturn(user);
    }

    @Test
    public void findCasinoUserByName() {
        CasinoUser found = userService.findCasinoUserByUsername(name);
        log.info("CasinoUser found = {}",found);

        assertThat(found)
                .isNotNull();
        assertThat(found.getNickname())
                .isEqualTo(name);
        assertThat(found.getBalance())
                .isEqualTo(balance);
    }

    @Test
    void findUserByUsername() {
        User found = userService.findUserByUsername(name);

        assertThat(found)
                .isNotNull();
        assertThat(found.getUsername())
                .isEqualTo(name);
        assertThat(found.getAuthority())
                .isEqualTo(authority);
    }
}