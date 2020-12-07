package com.agawrysiuk.casino.model.database;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "CASINO_USER")
public class CasinoUser {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "NICKNAME")
    private String nickname;
    @Column(name = "BALANCE")
    private double balance;
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
