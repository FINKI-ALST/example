package mk.ukim.finki.wp.lab.selenium;

import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumScenarioTest {
    @Autowired
    CourseService courseService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    StudentService studentService;


    private HtmlUnitDriver driver;


    //private static Course c1;
    //rivate static Course c2;
    private static Teacher t1;
    //private static Teacher t2;
    //private static Student s1;
    //rivate static Student s2;

    private static String admin = "admin";

    private static boolean dataInitialized = false;


    @BeforeEach
    private void setup() {
        this.driver = new HtmlUnitDriver(true);
        initData();
    }

    @AfterEach
    public void destroy() {
        if (this.driver != null) {
            this.driver.close();
        }
    }


    private void initData() {
        if (!dataInitialized) {
            t1=new Teacher(1L,"TeacherName","TeacherSurname");
            teacherService.save(t1);
            courseService.save(1L, "CourseName","CourseDescription", t1.getId(),"WINTER");
            dataInitialized = true;
        }
    }

    @Test
    public void testScenario() throws Exception {
        CoursePage coursePage = CoursePage.to(this.driver);
        coursePage.assertElemts(1, 0, 0, 0);
        LoginPage loginPage = LoginPage.openLogin(this.driver); //vraka login page

        coursePage = LoginPage.doLogin(this.driver, loginPage, admin, admin);
        coursePage.assertElemts(1, 1, 1, 1);
        String teacher=t1.getName()+" "+t1.getSurname();
        coursePage = AddOrEditCourse.addCourse(this.driver, "test1", "desc1", teacher,"SUMMER");
        coursePage.assertElemts(2, 2, 2,  1);



    }

}
