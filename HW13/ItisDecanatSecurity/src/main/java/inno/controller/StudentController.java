package inno.controller;


import inno.model.Score;
import inno.model.Student;
import inno.model.SubjectType;
import inno.model.User;
import inno.repository.ScoreRepository;
import inno.repository.StudentRepository;
import inno.repository.UsersRepository;
import inno.security.SecurityUtils;
import inno.service.StudentService;
import inno.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ScoreRepository scoreRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    UsersService usersService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexPage(ModelMap map){
        return "index";
    }



    @RequestMapping("/students")
    public String getAllStudents(@RequestParam(value = "group", required = false) String group, ModelMap map) {
        List<Student> students = studentRepository.findAll();
        if (group != null) {
            students = students.stream().filter(p -> p.getStudgroup().contains(group)).collect(Collectors.toList());
        }
        map.addAttribute("students", students);
        return "students/index";
    }

    @RequestMapping(value = "/students/add", method = RequestMethod.GET)
    public String addNewStudentPage(ModelMap map) {
        map.addAttribute("student", new Student());
        return "students/add";
    }

    @RequestMapping(value = "/students/add", method = RequestMethod.POST)
    public String addNewStudent(@ModelAttribute @Valid Student student, BindingResult result) {
        if (result.hasErrors()) {
            return "students/add";
        }

        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @RequestMapping(value = "/students/{student_id}", method = RequestMethod.GET)
    public String showStudent(@PathVariable("student_id") Integer studentId, ModelMap map) {
        Student student = studentRepository.findOne(studentId);
        student.setScores(studentRepository.findScoresByStudent(student));
        map.addAttribute("sumScore",student.getScores().stream().mapToInt(Score::getScore).sum());
        map.addAttribute("avgScore",student.getScores().stream().mapToInt(Score::getScore).average().orElse(0.0));
        map.addAttribute("student", student);
        map.addAttribute("score", new Score());
        map.addAttribute("subjects", SubjectType.values());
        return "/students/show";
    }

    @RequestMapping(value = "/students/{student_id}/deletescore/{score_id}", method = RequestMethod.GET)
    public String deleteScore(@PathVariable("student_id") Integer studentId, @PathVariable("score_id") Integer scoreId, ModelMap map) {
        scoreRepository.delete(scoreId);
        return "redirect:/students/"+studentId;
    }

/*    @RequestMapping(value = "/students/{student_id}", method = RequestMethod.DELETE)
    public String deleteScore(@PathVariable("student_id") Integer studentId, ModelMap map, @RequestBody Integer scoreId) {
        Score score = scoreRepository.findOne(scoreId);
        if (!userCanEditStudent(score.getStudent())) {
            throw new AccessDeniedException("Acces denied");
        }
        scoreRepository.delete(score);
        return "redirect:/students/"+studentId;
    }*/

    @RequestMapping(value = "/students/{student_id}", method = RequestMethod.POST)
    public String addScoreToStudent(@PathVariable("student_id") Integer studentId, ModelMap map, @ModelAttribute("score") @Valid Score score, BindingResult result) {
        if (result.hasErrors()) {
             return "/students/show";
        }
        Student student = studentRepository.findOne(studentId);
        score.setStudent(student);
        scoreRepository.save(score);
        return "redirect:/students/"+studentId;
    }

    @RequestMapping(value = "/students/{id}/delete", method = RequestMethod.GET)
    public String deleteStudent(@PathVariable("id") Integer id, ModelMap map) {
        Student student = studentRepository.findOne(id);
        if (!userCanEditStudent(student)) {
            throw new AccessDeniedException("Acces denied");
        }
        studentRepository.delete(student);
        return "redirect:/students";
    }

    @RequestMapping(value = "/students/{id}/edit", method = RequestMethod.GET)
    public String editStudent(@PathVariable("id") Integer id, ModelMap map) {
        Student student = studentRepository.findOne(id);
        if (!userCanEditStudent(student)) {
            throw new AccessDeniedException("Acces denied");
        }
        map.addAttribute("student", student);
        return "students/edit";
    }

    @RequestMapping(value = "/students/{id}/save", method = RequestMethod.POST)
    public String saveStudent(@ModelAttribute @Valid Student student, BindingResult result) {
        if (result.hasErrors()) {
            return "students/edit";
        }
        studentService.saveStudent(student);
        return "redirect:/students";
    }
    private boolean userCanEditStudent(Student student) {
        User currentUser = SecurityUtils.getCurrentUser();
        return currentUser != null && student.getUser().getId().equals(currentUser.getId());
    }
}
