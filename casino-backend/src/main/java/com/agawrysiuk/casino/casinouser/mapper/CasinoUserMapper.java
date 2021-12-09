package com.agawrysiuk.casino.casinouser.mapper;

import com.agawrysiuk.casino.casinouser.CasinoUser;
import com.agawrysiuk.casino.casinouser.dto.CasinoUserDto;
import org.mapstruct.Mapper;

@Mapper
public interface CasinoUserMapper {

    CasinoUserDto casinoUserDto(CasinoUser casinoUser);
}
