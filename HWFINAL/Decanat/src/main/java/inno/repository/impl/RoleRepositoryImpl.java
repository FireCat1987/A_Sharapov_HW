package inno.repository.impl;

import inno.model.Role;
import inno.repository.RoleRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
/*@Repository
@Transactional*/
public class RoleRepositoryImpl /*implements RoleRepository*/ {
/*    @PersistenceContext
    private
    EntityManager em;
    @Override
    public Role findByName(String name) {
        TypedQuery<Role> query = em.createQuery("SELECT role FROM Role role  WHERE role.name = :name", Role.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }*/
}
