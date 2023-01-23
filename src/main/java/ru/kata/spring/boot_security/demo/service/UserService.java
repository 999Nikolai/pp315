package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {


    void saveUser(User user);

    void updateUser(String username, User newUserData);

    void removeUserById(long id);

    User show(long id);

    List<User> getUser();
    User findByEmail(String name);
    User findByUsername(String username);

    @Override
    UserDetails loadUserByUsername(String username);
}
