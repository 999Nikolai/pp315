package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
@Repository
public class UserDaoImp implements UserDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getUser() {
        return entityManager.createQuery("from User ").getResultList();
    }
    @Override
    public User show(Long id) {
        return entityManager.find(User.class, id);
    }
    @Override
    public void save(User user) {
        entityManager.persist(user);
    }
    @Override
    public void update(User updatedUser) {
        entityManager.merge(updatedUser);
    }
    @Override
    public void delete(Long id) {
        entityManager.remove(show(id));
    }

    @Override
    public User findByEmail(String email) {
        Query query = entityManager.createQuery("from User where email = :email", User.class);
        query.setParameter("email", email);
        return (User) query.getSingleResult();
    }
    @Override
    public User findByUsername(String username) {
        Query query = entityManager.createQuery("from User where username = :username", User.class);
        query.setParameter("username", username);
        return (User) query.getSingleResult();
    }






}
