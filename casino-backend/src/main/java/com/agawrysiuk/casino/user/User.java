package com.agawrysiuk.casino.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USER")
public class User {

    @Id
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "ENABLED")
    private boolean enabled;
    @Column(name = "AUTHORITY")
    private String authority;

}
