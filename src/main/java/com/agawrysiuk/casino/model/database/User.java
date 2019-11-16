package com.agawrysiuk.casino.model.database;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    private String username;
    private String email;
    private String password;
    private boolean enabled;
    private String authority;

}
