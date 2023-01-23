package ru.kata.spring.boot_security.demo.model;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;

@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "firstname")
    @NotEmpty(message = "Напишите имя")
    @Size(min = 2, max = 30, message = "Имя короткое или длинное")
    private String username;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "age")
    private Long age;

    @Column(name = "password")
    private String password;


    @Column(name = "email")
    @NotEmpty(message = "Напишите email")
    @Email
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    public User() {
    }

    public User(String username, String lastname, Long age, String password, String email, Collection<Role> roles) {
        this.username = username;
        this.lastname = lastname;
        this.age = age;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public String listOfRoles() {

        StringBuilder listOfRoles = new StringBuilder();
        for (Role role: roles) {
            listOfRoles.append(role.getName() + " ");
        }
        return String.valueOf(listOfRoles);
    }

}
