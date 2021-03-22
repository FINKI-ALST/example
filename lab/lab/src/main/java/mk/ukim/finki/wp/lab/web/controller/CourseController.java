package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.enumeration.TypeStatus;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = {"/","/courses"})
public class CourseController {
    private final CourseService courseService;
    private final TeacherService teacherService;
    private final StudentService studentService;
    public CourseController(CourseService courseService, TeacherService teacherService, StudentService studentService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
        this.studentService = studentService;
    }
    @GetMapping
    public String getCoursePage(@RequestParam(required = false) String error, Model model)
    {
        if(error!=null && !error.isEmpty())
        {
            model.addAttribute("hasErrors",true);
            model.addAttribute("error",error);
        }
         model.addAttribute("CoursesList",courseService.listAll());
         return "listCourses";
    }
    @PostMapping
    public String courseChoosed(@RequestParam Long courseId, HttpServletRequest request,Model model)
    {
     request.getSession().setAttribute("courseId",courseId);
     model.addAttribute("students",studentService.listAll());
     return "redirect:/AddStudent";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/add-form")
    public String getAddCoursePage(@RequestParam(required = false) String error,Model model)
    {
        if(error!=null && !error.isEmpty())
        {
            model.addAttribute("hasError",true);
            model.addAttribute("errors",error);
        }
        List<Teacher> teacherList=this.teacherService.findAll();
        model.addAttribute("teachers",teacherList);
        model.addAttribute("status", TypeStatus.values());
        return "add-course";
    }
    @PostMapping("/add")
    public String saveCourse(@RequestParam(required = false) Long courseId,@RequestParam String name,@RequestParam String description,@RequestParam Long id, @RequestParam String typestatus)
    {

            if (courseId == null) {
                if (this.courseService.findCourseByName(name).isPresent()) {
                    return "redirect:/courses/add-form?error=Course already exists"; //sakame da dodademe so isto ime
                }
                else {
                        //si kreirame nov student
                        this.courseService.save(courseId, name,description,id,typestatus);
                }
            }
            else
            {
                //update
                 this.courseService.update(courseId,name,description,id,typestatus);
            }
        return "redirect:/courses";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/edit-form/{id}")
    public String getEditCoursePage(@PathVariable Long id, Model model)
    {

        if(courseService.findCourse(id).isPresent())
        {
            Course course=courseService.findCourse(id).get();
            List<Teacher> teacherList=this.teacherService.findAll();
            model.addAttribute("teachers",teacherList);
            model.addAttribute("course",course);
            model.addAttribute("status", TypeStatus.values());
            return "add-course";
        }
        return "redirect:/courses?error=CourseNotFound";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("delete/{id}")
    public String deleteCourse(@PathVariable Long id)
    {
        this.courseService.deleteById(id);
        return "redirect:/courses";
    }
    @PostMapping("/search")
    public String searchCourseByType(@RequestParam String search1,Model model)
    {
        List<Course> filteredCourses=courseService.findByAllAtributes(search1);
        model.addAttribute("filter",true);
        model.addAttribute("filteredCourses",filteredCourses);
        model.addAttribute("CoursesList",courseService.listAll());
        return "listCourses";
    }
    @GetMapping("/access_denied")
    public String getAccessDeniedPage()
    {
        return "access_denied";
    }
}
