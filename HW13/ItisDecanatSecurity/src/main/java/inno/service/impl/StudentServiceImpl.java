package inno.service.impl;

import inno.model.Student;
import inno.model.Users;
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
    StudentRepository repository;

    @Transactional
    @Override
    public void saveStudent(Student student) {
        Users user = SecurityUtils.getCurrentUser();
        student.setUser(user);
        // TODO использовать PostForm
        repository.save(student);
    }

}
