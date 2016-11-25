package inno.controller;


import inno.model.Score;
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
import javax.validation.constraints.Null;
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

    @RequestMapping(value = "/{student_id}", method = RequestMethod.GET)
    public String showStudent(@PathVariable("student_id") Integer studentId, ModelMap map) {
        Student student = studentRepository.find(studentId);
        student.setScores(scoreRepository.findByStudentId(studentId));
        map.addAttribute("sumScore",student.getScores().stream().mapToInt(Score::getScore).sum());
        map.addAttribute("avgScore",student.getScores().stream().mapToInt(Score::getScore).average().orElse(0.0));
        map.addAttribute("student", student);
        map.addAttribute("score", new Score());
        map.addAttribute("subjects", SubjectType.values());
        return "/students/show";
    }

    @RequestMapping(value = "/{student_id}/deletescore/{score_id}", method = RequestMethod.GET)
    public String deleteScore(@PathVariable("student_id") Integer studentId, @PathVariable("score_id") Integer scoreId, ModelMap map) {
        scoreRepository.remove(scoreId);
        return "redirect:/students/"+studentId;
    }

    /*@RequestMapping(value = "/{student_id}", method = RequestMethod.DELETE)
    public String deleteScore(@PathVariable("student_id") Integer studentId, ModelMap map, @RequestBody String scoreId) {
        System.out.println("Delete score id " + Integer.parseInt(scoreId));
        if(!(scoreRepository.find(Integer.parseInt(scoreId)) == null)){
            scoreRepository.remove(Integer.parseInt(scoreId));
            return "redirect:/students/"+studentId;
        }
*//*        Student student = studentRepository.find(studentId);
        student.setScores(scoreRepository.findByStudentId(studentId));
        map.addAttribute("student", student);
        map.addAttribute("subjects", SubjectType.values());*//*
        return "/students/show";
    }
*/
    @RequestMapping(value = "/{student_id}", method = RequestMethod.POST)
    public String addScoreToStudent(@PathVariable("student_id") Integer studentId, ModelMap map, @ModelAttribute("score") @Valid Score score, BindingResult result) {

        if (result.hasErrors()) {
            Student student = studentRepository.find(studentId);
            student.setScores(scoreRepository.findByStudentId(studentId));
            map.addAttribute("student", student);
            map.addAttribute("subjects", SubjectType.values());
            return "/students/show";
        }
        Student student = studentRepository.find(studentId);
        score.setStudent(student);
        scoreRepository.add(score);
        return "redirect:/students/"+studentId;
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
