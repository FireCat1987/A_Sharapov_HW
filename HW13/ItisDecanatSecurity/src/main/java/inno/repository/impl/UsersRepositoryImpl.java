package inno.repository.impl;

/*
@Repository
@Transactional*/
public class UsersRepositoryImpl/* implements UsersRepository*/ {

   /* @PersistenceContext
    private
    EntityManager em;

    @Override
    public User findById(Integer id) {
        return em.find(User.class, id);
    }

    @Override
    public User findByLogin(String login){
        TypedQuery<User> query = em.createQuery(
                "SELECT usr from User usr WHERE usr.login = :login", User.class);
        query.setParameter("login", login);
        List<User> results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        } else {
            return results.get(0);
        }
    }

    @Override
    public boolean add(User users) {
        if(findByLogin(users.getLogin()) != null){
            return false;
        }
        em.persist(users);
        return true;
    }

    @Override
    public void update(User users) {
        em.merge(users);
    }

    @Override
    public boolean remove(Integer id) {
*//*        Student student = em.find(Student.class, id);
        em.remove(student);*//*
        em.remove(em.find(User.class, id));
        return true;
    }*/
}
