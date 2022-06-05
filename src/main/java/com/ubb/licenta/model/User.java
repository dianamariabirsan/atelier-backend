package com.ubb.licenta.model;

import com.ubb.licenta.model.enums.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="users")
@Getter
@Setter
@ToString
public class User extends BaseEntity<Long> implements Serializable {
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String password;
    private Role role;

    public User(Long aLong, String name, String phoneNumber, String email, String password, Role role) {
        super(aLong);
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User() {
    }
}
