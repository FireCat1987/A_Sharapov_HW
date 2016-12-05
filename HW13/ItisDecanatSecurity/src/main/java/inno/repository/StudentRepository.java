package inno.repository;

import inno.model.Score;
import inno.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface  StudentRepository extends JpaRepository<Student, Integer> {

    @Query("select c from Score c where c.student = :student")
    List<Score> findScoresByStudent(@Param("student") Student student);

}
