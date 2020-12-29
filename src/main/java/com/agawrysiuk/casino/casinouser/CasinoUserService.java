package com.agawrysiuk.casino.casinouser;

import com.agawrysiuk.casino.user.exception.UserDoesntExistException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
class CasinoUserService {

    private final CasinoUserRepository casinoUserRepository;

    public CasinoUserDto get(String nickname) {
        CasinoUser casinoUser = casinoUserRepository.findByNickname(nickname)
                .orElseThrow(UserDoesntExistException::new);

        return CasinoUserDto.builder()
                .nickname(casinoUser.getNickname())
                .firstName(casinoUser.getFirstname())
                .secondName(casinoUser.getSecondname())
                .balance(casinoUser.getBalance())
                .birthDate(casinoUser.getBirthdate())
                .country(casinoUser.getCountry())
                .build();
    }

    @Transactional
    public CasinoUserDto update(CasinoUserDto casinoUserDto) {
        CasinoUser casinoUser = casinoUserRepository.findByNickname(casinoUserDto.getNickname())
                .orElseThrow(UserDoesntExistException::new);

        log.info("User update for {} started", casinoUserDto.getNickname());

        casinoUser.setCountry(casinoUserDto.getCountry());
        casinoUser.setFirstname(casinoUserDto.getFirstName());
        casinoUser.setSecondname(casinoUserDto.getSecondName());
        casinoUser.setBirthdate(casinoUserDto.getBirthDate());

        CasinoUser saved = casinoUserRepository.save(casinoUser);

        log.info("User update completed! User = {}", saved);

        return  CasinoUserDto.builder()
                .nickname(saved.getNickname())
                .firstName(saved.getFirstname())
                .secondName(saved.getSecondname())
                .balance(saved.getBalance())
                .birthDate(saved.getBirthdate())
                .country(saved.getCountry())
                .build();
    }
}
