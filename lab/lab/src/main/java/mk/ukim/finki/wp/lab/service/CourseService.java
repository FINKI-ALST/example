package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Student> listStudentsByCourse(Long courseId);
    Course addStudentInCourse(String username, Long courseId);
    List<Course> listAll();
    Optional<Course> findCourse(Long courseId);
    Optional<Course> findCourseByName(String name);
    Optional<Course> save(Long courseId, String name, String description, Long id, String typestatus);
    Optional<Course> update(Long courseId,String name,String description,Long id,String typestatus);
    List<Course> findCourseByEnum(String typeStatus);
    void deleteById(Long id);
    List<Course> findByAllAtributes(String text);
}
