package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {


    void saveUser(User user);

    void updateUser(int id, User user);

    void removeUserById(long id);

    User show(long id);

    List<User> getUser();

    User findByUsername(String username);

    @Override
    UserDetails loadUserByUsername(String username);
}
