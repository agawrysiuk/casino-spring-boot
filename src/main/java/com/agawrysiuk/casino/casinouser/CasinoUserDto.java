package com.agawrysiuk.casino.casinouser;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CasinoUserDto {
    private String nickname;
    private double balance;
    private String firstName;
    private String secondName;
    private Date birthDate;
    private String country;
}
