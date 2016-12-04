package inno.controller;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.Collection;

@RunWith(Parameterized.class)
public class StudentControllerTest {
    StudentController studentController;
    ModelMap modelMap;
    String page;

    @BeforeClass
    @AfterClass
    public static void bac(){

    }

    @Before
    public void setUp(){
        studentController = new StudentController();
        modelMap = new ModelMap();
    }
    @After
    public void tearDown(){

    }

    public StudentControllerTest(String page) {
        this.page = page;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        ArrayList<Object[]> result = new ArrayList<>();
        result.add(new Object[]{"index"});

        return result;
    }

    @Test
    public void testGetIndexPage() {
        String str = studentController.indexPage(modelMap);
        Assert.assertEquals(page, str);
    }


}
