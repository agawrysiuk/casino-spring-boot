package com.agawrysiuk.casino.casinouser.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditCasinoUserRequest {
    private String firstName;
    private String secondName;
    private Date birthDate;
    private String country;
}
