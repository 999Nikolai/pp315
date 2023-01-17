package ru.kata.spring.boot_security.demo.dao;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDao implements Converter<String, ru.kata.spring.boot_security.demo.model.Role> {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Role> findAllRoles() {
        return entityManager.createQuery("from Role", Role.class).getResultList();
    }

    public ru.kata.spring.boot_security.demo.model.Role convert(String id) {
        ru.kata.spring.boot_security.demo.model.Role role = new ru.kata.spring.boot_security.demo.model.Role();
        role.setId(Long.valueOf(id));
        return role;
    }

//    public Role getRoleById(long id) {
//        return entityManager.find(Role.class, id);
//    }

}
