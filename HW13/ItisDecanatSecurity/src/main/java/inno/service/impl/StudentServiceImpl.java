package inno.service.impl;

import inno.model.Score;
import inno.model.Student;
import inno.model.Users;
import inno.repository.ScoreRepository;
import inno.repository.StudentRepository;
import inno.security.SecurityUtils;
import inno.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository repository;
    @Autowired
    ScoreRepository scoreRepository;

    @Transactional
    @Override
    public void saveStudent(Student student) {
        Users user = SecurityUtils.getCurrentUser();
        student.setUser(user);
        List<Score> scores = repository.findScoresByStudent(student);
        student.setScores(scores);
        // TODO использовать StudentForm
        repository.save(student);
    }

    public String stringReturner(String s) {
        return s;
    }

}
