package inno.controller;

import inno.model.Student;
import inno.repository.StudentRepository;
import inno.service.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:dispatcher-servlet.xml"})
public class StudentControllerTestMVC {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentService studentService;
    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Before
    public void setUp() throws Exception {
/*        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                //.addFilter(springSecurityFilterChain)
                .dispatchOptions(true)
                .build();*/
    }

    @Test
    public void indexPage() throws Exception {
/*        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));*/
    }

    @Test
    public void getAllStudents() throws Exception {
/*        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/index"))
                .andExpect(model().attributeExists("students"));*/
    }

    @Test
    public void addNewStudentPage() throws Exception {
       /* mockMvc.perform(get("/students/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/add"))
                .andExpect(model().attributeExists("student"));*/
    }

    @Test
    public void addNewStudentWithNoRules() throws Exception {
/*        List<Student> students = studentRepository.findAll();
        assertEquals(students.size(),0);*/
    /*    mockMvc.perform(post("/students/add")
                .param("firstname", "Шарапов")
                .param("surname", "Аркадий")
                .param("lastname", "Валерьевич")
                .param("studgroup", "1123"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/login"));*/

/*        students = studentRepository.findAll();
        assertNotEquals(students.size(),0);
        assertNotNull(students.get(0).getId());

        studentRepository.delete(students.get(0));*/

    }

    @Test
    public void addNewStudentWithRules() throws Exception {
       /* List<Student> students = studentRepository.findAll();
        assertEquals(students.size(), 0);
        mockMvc.perform(post("/students/add")
                .param("firstname", "Шарапов")
                .param("surname", "Аркадий")
                .param("lastname", "Валерьевич")
                .param("studgroup", "1123"))
                .andExpect(status().isOk())
                .andExpect(redirectedUrl("/students"));

        students = studentRepository.findAll();
        assertNotEquals(students.size(),0);
        assertNotNull(students.get(0).getId());

        studentRepository.delete(students.get(0));*/

    }

    @Test
    public void showStudent() throws Exception {

    }

    @Test
    public void deleteScore() throws Exception {

    }

    @Test
    public void addScoreToStudent() throws Exception {

    }

    @Test
    public void deleteStudent() throws Exception {

    }

    @Test
    public void editStudent() throws Exception {

    }

    @Test
    public void saveStudent() throws Exception {

    }

    @Test
    public void saveStudent1() throws Exception {

    }

}