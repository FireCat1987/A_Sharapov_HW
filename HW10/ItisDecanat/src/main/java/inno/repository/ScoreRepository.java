package inno.repository;

import inno.model.Score;

import java.util.List;

public interface ScoreRepository {
    List<Score> findAll();

    Score find(Integer id);

    boolean add(Score score);

    boolean remove(Integer id);

    List<Score> findByStudentId(Integer studentId);
}
