package inno.repository.impl;


import inno.model.Score;
import inno.model.Student;
import inno.repository.StudentRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class StudentRepositoryImpl implements StudentRepository {

    @PersistenceContext
    private
    EntityManager em;

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = em.createQuery("SELECT student FROM Student student", Student.class);
        return query.getResultList();
    }

    @Override
    public Student find(Integer id) {

        return em.find(Student.class, id);
    }

    @Override
    public boolean add(Student student) {
        em.persist(student);
        return true;
    }

    @Override
    public void update(Student student) {
        TypedQuery<Score> query = em.createQuery(
                "SELECT score from Score score WHERE score.student.id = :studentId", Score.class);
        query.setParameter("studentId", student.getId());
        student.setScores(query.getResultList());
        em.merge(student);
    }

    @Override
    public boolean remove(Integer id) {
        Student student = em.find(Student.class, id);
        TypedQuery<Score> query = em.createQuery(
                "SELECT score from Score score WHERE score.student.id = :studentId", Score.class);
        query.setParameter("studentId", id);
        student.setScores(query.getResultList());
        em.remove(student);
       /* em.remove(em.find(Student.class, id));*/
        return true;
    }

    @Override
    public List<Student> findByGroup(String studgroup) {
        TypedQuery<Student> query = em.createQuery(
                "SELECT student from Student student WHERE student.studgroup LIKE :studgroup", Student.class);
        query.setParameter("studgroup", studgroup);
        return query.getResultList();
    }
}
