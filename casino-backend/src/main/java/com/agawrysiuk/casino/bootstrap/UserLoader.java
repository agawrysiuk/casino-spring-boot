package com.agawrysiuk.casino.bootstrap;

import com.agawrysiuk.casino.casinouser.CasinoUser;
import com.agawrysiuk.casino.casinouser.CasinoUserRepository;
import com.agawrysiuk.casino.casinouser.CasinoUserService;
import com.agawrysiuk.casino.casinouser.dto.EditCasinoUserRequest;
import com.agawrysiuk.casino.user.UserService;
import com.agawrysiuk.casino.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;

@Component
@RequiredArgsConstructor
public class UserLoader implements CommandLineRunner {

    private final UserService userService;
    private final CasinoUserService casinoUserService;
    private final CasinoUserRepository repository;

    @Override
    public void run(String... args) throws Exception {
        loadAdmin();
    }

    private void loadAdmin() {
        userService.registerNewUserAccount(UserDto.builder()
                        .username("admin")
                        .email("admin@admin.com.pl")
                        .password("admin")
                        .matchingPassword("admin")
                        .build()
                ,true
        );

        casinoUserService.update("admin", EditCasinoUserRequest.builder()
                .firstName("Adam")
                .secondName("Miałczyński")
                .country("Poland")
                .birthDate(new Date(1989, 11, 1))
                .build()
        );

        CasinoUser user = repository.findByNickname("admin").get();
        user.updateBalance(BigDecimal.valueOf(50));
        repository.save(user);
    }
}
