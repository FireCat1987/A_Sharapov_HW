package inno.repository.impl;

import inno.model.Score;
import inno.model.Student;
import inno.repository.ScoreRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
@Repository
@Transactional
public class ScoreRepositoryImpl implements ScoreRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Score> findAll() {
        TypedQuery<Score> query = em.createQuery("SELECT score FROM Score score", Score.class);
        return query.getResultList();
    }

    @Override
    public Score find(Integer id) {
        Score score = em.find(Score.class, id);
        return score;
    }

    @Override
    public boolean add(Score score) {
        em.persist(score);
        return true;
    }

    @Override
    public boolean remove(Integer id) {
        em.remove(em.find(Score.class, id));
        return true;
    }

    @Override
    public List<Score> findByStudentId(Integer studentId) {
        TypedQuery<Score> query = em.createQuery(
                "SELECT score from Score score WHERE score.student.id = :studentId", Score.class);
        query.setParameter("studentId", studentId);
        return query.getResultList();
    }
}
