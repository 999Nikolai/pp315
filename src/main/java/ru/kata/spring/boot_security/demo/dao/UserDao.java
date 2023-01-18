package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {

    List<User> getUser();
    User show(Long id);
    void save(User user);
    void update(User updatedUser);
    void delete(Long id);

//    User findByEmail(String email);
    User findByUsername(String username);
}
