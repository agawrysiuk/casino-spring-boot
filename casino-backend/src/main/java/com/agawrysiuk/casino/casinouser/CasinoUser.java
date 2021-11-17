package com.agawrysiuk.casino.casinouser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.sql.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CASINO_USER")
public class CasinoUser {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "CE0AOFQ")
    private Integer id;
    @Column(name = "NICKNAME")
    private String nickname;
    @Digits(integer=12, fraction=2)
    @Column(name = "BALANCE")
    private BigDecimal balance;
    @Column(name = "IS_ACTIVE")
    private boolean isactive;
    @Column(name = "FIRST_NAME")
    private String firstname;
    @Column(name = "SECOND_NAME")
    private String secondname;
    @Column(name = "BIRTH_DATE")
    private Date birthdate;
    @Column(name = "COUNTRY")
    private String country;
}
