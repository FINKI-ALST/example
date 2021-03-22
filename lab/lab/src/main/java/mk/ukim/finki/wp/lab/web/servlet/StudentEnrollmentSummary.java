package mk.ukim.finki.wp.lab.web.servlet;

import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.exceptions.UserNotFound;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.GradeService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "student-enrollment-summary",urlPatterns = "/StudentEnrollmentSummary")
public class StudentEnrollmentSummary extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final CourseService courseService;
    private final StudentService studentService;
    private final GradeService gradeService;
    public StudentEnrollmentSummary(SpringTemplateEngine springTemplateEngine, CourseService courseService, StudentService studentService, GradeService gradeService) {
        this.springTemplateEngine = springTemplateEngine;
        this.courseService = courseService;
        this.studentService = studentService;
        this.gradeService = gradeService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext=new WebContext(req,resp,req.getServletContext());
        Course course=courseService.findCourse((long)req.getSession().getAttribute("courseId")).orElseThrow(UserNotFound::new);
        webContext.setVariable("courseN",course );
        webContext.setVariable("studentsCourse",courseService.listStudentsByCourse((long)req.getSession().getAttribute("courseId")));
        webContext.setVariable("grades",gradeService.findGradesOfCourse(course.getCourseId()));
        List<Student> searchedStudents=(List<Student>) req.getSession().getAttribute("searchedstudents");

        if(searchedStudents!=null)
        {
            webContext.setVariable("studentsS",searchedStudents);
            req.getSession().setAttribute("searchedstudents",null);
        }
        resp.setContentType("application/xhtml+xml");
        springTemplateEngine.process("studentsInCourse",webContext,resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String textSearch=req.getParameter("search");
        if(!textSearch.isEmpty())
        {List<Student> students=studentService.searchByNameOrSurname(textSearch);
        req.getSession().setAttribute("searchedstudents",students);}
        else
        {
            req.getSession().setAttribute("searchedstudents",null);
        }
        resp.sendRedirect("/StudentEnrollmentSummary");
    }
}
