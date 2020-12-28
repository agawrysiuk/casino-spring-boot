package com.agawrysiuk.casino.casinouser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class CasinoUserService {

    private final CasinoUserRepository casinoUserRepository;

    public CasinoUserDto get(String nickname) {
        CasinoUser casinoUser = casinoUserRepository.findByNickname(nickname);

        return CasinoUserDto.builder()
                .nickname(casinoUser.getNickname())
                .firstName(casinoUser.getFirstname())
                .secondName(casinoUser.getSecondname())
                .balance(casinoUser.getBalance())
                .birthDate(casinoUser.getBirthdate())
                .country(casinoUser.getCountry())
                .build();
    }
}
