package com.maybe.app.model;

import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
public class User {

    private Long id;
    private String username;
    private String password;
    private String lastname;
    private String email;
    private String phone;
    private Set<Role> roles;

    public User() {
    }

}
