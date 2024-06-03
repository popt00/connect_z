package ca.parimal.connectz.model.dao;

import ca.parimal.connectz.model.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public class UserDaoImpl implements UserDao{
    private EntityManager entityManager;

    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> fethAll() {
        TypedQuery<User> thequery = entityManager.createQuery("FROM User", User.class);
        List<User> users= thequery.getResultList();
        return users;
    }

    @Override
    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void save() {
        User user = new User("Parimal",229);
        user.setActive(1);
        user.setPassword("sdf");
        entityManager.persist(user);
    }

}
