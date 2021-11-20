package com.agawrysiuk.casino.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;
import java.util.Collections;

@TestConfiguration
public class SpringWebSecurityConfig {

    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        User basicActiveUser = new User("Basic_User", "password", Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));

        return new InMemoryUserDetailsManager(Arrays.asList(
                basicActiveUser
        ));
    }
}
