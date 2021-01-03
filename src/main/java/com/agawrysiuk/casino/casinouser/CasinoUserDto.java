package com.agawrysiuk.casino.casinouser;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CasinoUserDto {
    private String nickname;
    private BigDecimal balance;
    private String firstName;
    private String secondName;
    private Date birthDate;
    private String country;
}
