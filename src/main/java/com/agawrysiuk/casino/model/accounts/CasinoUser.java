package com.agawrysiuk.casino.model.accounts;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "casinouser")
public class CasinoUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nickname;
    private double balance;
}
