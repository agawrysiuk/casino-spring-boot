package com.agawrysiuk.casino.casinouser;

import com.agawrysiuk.casino.casinouser.dto.EditCasinoUserRequest;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter(AccessLevel.PRIVATE)
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

    public CasinoUser(String nickname) {
        this.nickname = nickname;
    }

    void update(EditCasinoUserRequest request) {
        this.setCountry(request.getCountry());
        this.setFirstname(request.getFirstName());
        this.setSecondname(request.getSecondName());
        this.setBirthdate(request.getBirthDate());
    }

    public void updateBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
