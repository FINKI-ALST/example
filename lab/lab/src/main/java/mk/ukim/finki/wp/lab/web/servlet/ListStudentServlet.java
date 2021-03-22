package mk.ukim.finki.wp.lab.web.servlet;


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

@WebServlet(name = "list-student-servlet", urlPatterns = "/AddStudent")
public class ListStudentServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final StudentService studentService;
    private final CourseService courseService;
    private final GradeService gradeService;


    public ListStudentServlet(SpringTemplateEngine springTemplateEngine, StudentService studentService, CourseService courseService, GradeService gradeService) {
        this.springTemplateEngine = springTemplateEngine;
        this.studentService = studentService;
        this.courseService = courseService;
        this.gradeService = gradeService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext=new WebContext(req,resp,req.getServletContext());
        webContext.setVariable("students",studentService.listAll());
        resp.setContentType("application/xhtml+xml");
        springTemplateEngine.process("listStudents.html",webContext,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // courseService.addStudentInCourse(req.getParameter("size"),(long) req.getSession().getAttribute("courseId"));
        gradeService.addStudentInCourseWithGrade(req.getParameter("size"),(long)req.getSession().getAttribute("courseId"),req.getParameter("studentgrade").charAt(0));
        resp.sendRedirect("/StudentEnrollmentSummary");
    }
}
