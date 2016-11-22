package inno.controller;


import inno.model.Student;
import inno.model.SubjectType;
import inno.repository.ScoreRepository;
import inno.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ScoreRepository scoreRepository;

    @RequestMapping
    public String getAllStudents(@RequestParam(value = "group", required = false) String group, ModelMap map) {
        List<Student> students = studentRepository.findAll();
        if (group != null) {
            students = students.stream().filter(p -> p.getStudgroup().contains(group)).collect(Collectors.toList());
        }
        map.addAttribute("students", students);
        return "students/index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addNewStudentPage(ModelMap map) {
        map.addAttribute("student", new Student());
        return "students/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addNewStudent(@ModelAttribute @Valid Student student, BindingResult result) {
        if (result.hasErrors()) {
            return "students/add";
        }
        studentRepository.add(student);
        return "redirect:/students";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showStudent(@PathVariable("id") Integer id, ModelMap map) {
        Student student = studentRepository.find(id);
        student.setScores(scoreRepository.findByStudentId(id));
        map.addAttribute("student", student);
        map.addAttribute("subjects", SubjectType.values());
        //map.addAttribute("comments", commentRepository.findByPostId(id));
        return "/students/show";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String deleteStudent(@PathVariable("id") Integer id, ModelMap map) {
        studentRepository.remove(id);
        return "redirect:/students";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String editStudent(@PathVariable("id") Integer id, ModelMap map) {
        Student student = studentRepository.find(id);

        map.addAttribute("student", student);
        return "students/edit";
    }

    @RequestMapping(value = "/{id}/save", method = RequestMethod.POST)
    public String saveStudent(@ModelAttribute @Valid Student student, BindingResult result) {
        if (result.hasErrors()) {
            return "students/edit";
        }
        studentRepository.update(student);
        return "redirect:/students";
    }
}
