package com.agawrysiuk.casino.casinouser;

import com.agawrysiuk.casino.casinouser.dto.CasinoUserDto;
import com.agawrysiuk.casino.casinouser.dto.EditCasinoUserRequest;
import com.agawrysiuk.casino.casinouser.mapper.CasinoUserMapper;
import com.agawrysiuk.casino.user.exception.UserDoesntExistException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
public class CasinoUserService {

    private final CasinoUserRepository casinoUserRepository;
    private final CasinoUserMapper casinoUserMapper;

    public CasinoUserDto get(String nickname) {
        CasinoUser casinoUser = casinoUserRepository.findByNickname(nickname)
                .orElseThrow(UserDoesntExistException::new);

        return map(casinoUser);
    }

    @Transactional(rollbackFor = UserDoesntExistException.class)
    public CasinoUserDto update(String nickname, EditCasinoUserRequest editCasinoUserRequest) {
        CasinoUser casinoUser = casinoUserRepository.findByNickname(nickname)
                .orElseThrow(UserDoesntExistException::new);

        casinoUser.update(editCasinoUserRequest);

        CasinoUser saved = casinoUserRepository.save(casinoUser);

        return map(saved);
    }

    private CasinoUserDto map(CasinoUser user) {
        return casinoUserMapper.casinoUserDto(user);
    }
}
