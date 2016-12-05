package inno.repository.impl;

import inno.model.Student;
import inno.model.Users;
import inno.repository.UsersRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
/*
@Repository
@Transactional*/
public class UsersRepositoryImpl/* implements UsersRepository*/ {

   /* @PersistenceContext
    private
    EntityManager em;

    @Override
    public Users findById(Integer id) {
        return em.find(Users.class, id);
    }

    @Override
    public Users findByLogin(String login){
        TypedQuery<Users> query = em.createQuery(
                "SELECT usr from Users usr WHERE usr.login = :login", Users.class);
        query.setParameter("login", login);
        List<Users> results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        } else {
            return results.get(0);
        }
    }

    @Override
    public boolean add(Users users) {
        if(findByLogin(users.getLogin()) != null){
            return false;
        }
        em.persist(users);
        return true;
    }

    @Override
    public void update(Users users) {
        em.merge(users);
    }

    @Override
    public boolean remove(Integer id) {
*//*        Student student = em.find(Student.class, id);
        em.remove(student);*//*
        em.remove(em.find(Users.class, id));
        return true;
    }*/
}
