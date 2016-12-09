package inno.service.impl;

import inno.model.Student;
import inno.model.User;
import inno.repository.ScoreRepository;
import inno.repository.StudentRepository;
import inno.security.SecurityUtils;
import inno.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    ScoreRepository scoreRepository;

    @Transactional
    @Override
    public void saveStudent(Student student) {
        User user = SecurityUtils.getCurrentUser();
        student.setUser(user);
/*        List<Score> scores = scoreRepository.findScoresByStudent(student);
        student.setScores(scores);*/
        // TODO использовать StudentForm
        studentRepository.save(student);
    }

    public String stringReturner(String s) {
        return s;
    }

}
