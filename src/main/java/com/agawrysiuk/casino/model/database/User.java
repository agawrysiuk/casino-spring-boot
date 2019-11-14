package com.agawrysiuk.casino.model.database;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    private String username;
    private String email;
    private String password;
    private boolean enabled;
    private String confirmationToken;
    private String authority;

}
