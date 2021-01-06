package com.agawrysiuk.casino;

import com.agawrysiuk.casino.casinouser.CasinoUserDto;
import com.agawrysiuk.casino.casinouser.CasinoUserService;
import com.agawrysiuk.casino.user.dto.UserDto;
import com.agawrysiuk.casino.user.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.Date;

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

        context.getBean(CasinoUserService.class).update(CasinoUserDto.builder()
                .nickname("admin")
                .firstName("Adam")
                .secondName("Miałczyński")
                .country("Poland")
                .birthDate(new Date(1989, 11, 1))
                .build()
        );

    }

}
