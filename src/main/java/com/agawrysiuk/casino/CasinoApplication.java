package com.agawrysiuk.casino;

import com.agawrysiuk.casino.model.database.UserDto;
import com.agawrysiuk.casino.model.database.UserRepository;
import com.agawrysiuk.casino.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CasinoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CasinoApplication.class, args);
        context.getBean(UserService.class).registerNewUserAccount(UserDto.builder()
                .username("admin")
                .email("admin@admin.com.pl")
                .password("admin")
                .matchingPassword("admin")
                .build()
                ,true
        );
    }

}
