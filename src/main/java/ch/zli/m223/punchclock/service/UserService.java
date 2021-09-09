package ch.zli.m223.punchclock.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.domain.Project;
import ch.zli.m223.punchclock.domain.User;

@ApplicationScoped
public class UserService {
    @Inject
    private EntityManager entityManager;

    @Transactional
    public User createUser(User user) {
        entityManager.persist(user);
        return user;
    }

    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        var query = entityManager.createQuery("FROM User");
        return query.getResultList();
    }

    @Transactional
    public void deleteUser(Long id){

        User removeUser = getUserById(id);

        entityManager.remove(removeUser);
    }

    @Transactional
    public User getUserById(Long id){
        return entityManager.find(User.class, id);
    };

    @Transactional
    public void update(User user){
        entityManager.merge(user);
    }

}
