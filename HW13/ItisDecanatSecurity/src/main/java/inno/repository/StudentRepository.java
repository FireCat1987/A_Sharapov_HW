package inno.repository;

import inno.model.Student;

import java.util.List;

public interface  StudentRepository {

    List<Student> findAll();

    Student find(Integer id);

    boolean add(Student student);

    void update(Student student);

    boolean remove(Integer id);

    List<Student> findByGroup(String text);
}
