package mk.ukim.finki.wp.lab;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class LabApplicationTests {


    MockMvc mockMvc;


//    @Autowired
//    CourseService courseService;
//
//    @Autowired
//    TeacherService teacherService;
//
//    @Autowired
//    StudentService studentService;
//
//
//
//    private static Course c1;
//    private static Teacher t1;
//    private static boolean dataInitialized = false;

    @BeforeEach
    public void setup(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
      //  initData();
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void testGetCourses() throws Exception {
        MockHttpServletRequestBuilder coursesRequest = MockMvcRequestBuilders.get("/courses");
        this.mockMvc.perform(coursesRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("CoursesList"))
                .andExpect(MockMvcResultMatchers.view().name("listCourses"));

    }



}
