package com.agawrysiuk.casino.model.database;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "casinouser")
public class CasinoUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nickname;
    private double balance;
    private boolean isactive;
    private String firstname;
    private String secondname;
    private Date birthdate;
    private String country;
}
