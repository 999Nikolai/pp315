package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleDao {
    Role convert(String id);

    List<Role> findAllRoles();

    Role getRoleById(long id);
}