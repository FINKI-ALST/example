package mk.ukim.finki.wp.lab.web.rest;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/courses")
public class CourseRestController {
    private final CourseService courseService;

    public CourseRestController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> findAll() {
        return this.courseService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> findCourse(@PathVariable Long courseId) {
        return this.courseService.findCourse(courseId)
                .map(course -> ResponseEntity.ok().body(course))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/add")
    public ResponseEntity<Course> save(@PathVariable Long courseId, @RequestParam String name, @RequestParam String description, @RequestParam Long id, @RequestParam String typestatus) {
        return this.courseService.save(courseId,name,description,id,typestatus)
                .map(course -> ResponseEntity.ok().body(course))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCourse(@PathVariable Long id) {
        this.courseService.deleteById(id);
        if(this.courseService.findCourse(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }




}




