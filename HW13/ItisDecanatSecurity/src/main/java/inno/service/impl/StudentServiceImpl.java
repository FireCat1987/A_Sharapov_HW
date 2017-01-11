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
        student.setScores(scoreRepository.findScoresByStudent(student));
        student.setUser(user);
        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Student student) {
        student.getUser().getStudents().remove(student);
        studentRepository.delete(student);
    }

    public String stringReturner(String s) {
        return s;
    }

}
