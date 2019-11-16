package com.agawrysiuk.casino.model.database;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "casinouser")
public class CasinoUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nickname;
    private double balance;
    private boolean isactive;
    private String firstname;
    private String secondname;
    private Date birthdate;
    private String country;


//    ALTER TABLE casinouser ADD COLUMN isActive boolean;
//    ALTER TABLE casinouser ADD COLUMN firstname varchar(255);
//    ALTER TABLE casinouser ADD COLUMN secondname varchar(255);
//    ALTER TABLE casinouser ADD COLUMN dateOfBirth DATE;
//    ALTER TABLE casinouser ADD COLUMN country varchar(100);
}
