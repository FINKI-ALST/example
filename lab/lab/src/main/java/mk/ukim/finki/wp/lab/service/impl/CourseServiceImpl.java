package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.enumeration.TypeStatus;
import mk.ukim.finki.wp.lab.model.exceptions.CourseNotFound;
import mk.ukim.finki.wp.lab.model.exceptions.TeacherNotFoundException;
import mk.ukim.finki.wp.lab.model.exceptions.UserNotFound;
import mk.ukim.finki.wp.lab.repository.jpa.CourseRepository;
import mk.ukim.finki.wp.lab.repository.jpa.GradeRepository;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepository;
import mk.ukim.finki.wp.lab.repository.jpa.TeacherRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final GradeRepository gradeRepository;
    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository, TeacherRepository teacherRepository, GradeRepository gradeRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.gradeRepository = gradeRepository;
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        return courseRepository.findById(courseId).get().getStudents();
    }
    @Transactional
    @Override
    public Course addStudentInCourse(String username, Long courseId) {
        if(username.isEmpty())
        {
            throw new IllegalArgumentException();
        }
        Student student=studentRepository.findByUsername(username).orElseThrow(UserNotFound::new);
        Course course=courseRepository.findById(courseId).orElseThrow(CourseNotFound::new);
        courseRepository.findById(courseId).get().getStudents().removeIf(i->i.getUsername().equals(username));
        courseRepository.findById(courseId).get().getStudents().add(student);
        return course;

    }
    public List<Course> listAll()
    {
         List<Course> courses=courseRepository.findAll();
         Collections.sort(courses);
         return courses;
    }
    public Optional<Course> findCourse(Long courseId)
    {

        return courseRepository.findById(courseId);
    }

    @Override
    public Optional<Course> findCourseByName(String name) {

        return courseRepository.findByName(name);
    }

    @Override
    public Optional<Course> save(Long courseId, String name, String description, Long id, String typestatus) {
        Teacher teacher=this.teacherRepository.findById(id).orElseThrow(()-> new TeacherNotFoundException(id));
        TypeStatus typeStatus=TypeStatus.valueOf(typestatus);
        Course course=new Course(name,description,teacher,typeStatus);
        return Optional.of(courseRepository.save(course));
    }

    @Override
    @Transactional
    public Optional<Course> update(Long courseId, String name, String description, Long id,String typestatus) {
        Teacher teacher=this.teacherRepository.findById(id).orElseThrow(()->new TeacherNotFoundException(id));
        TypeStatus typeStatus=TypeStatus.valueOf(typestatus);
        List<Student> students=courseRepository.findById(courseId).get().getStudents();
        courseRepository.deleteById(courseId);
        Course course=new Course(courseId,name,description,students,teacher,typeStatus);
        return Optional.of(courseRepository.save(course));
    }

    @Override
    public List<Course> findCourseByEnum(String typeStatus) {
        return courseRepository.findAllByTypeStatus(TypeStatus.valueOf(typeStatus));
    }
    @Transactional
    @Override
    public void deleteById(Long id) {
        Course course=courseRepository.findById(id).orElseThrow(()->new CourseNotFound());
        gradeRepository.deleteAllByCourse(course);
        this.courseRepository.deleteById(id);
    }

    @Override
    public List<Course> findByAllAtributes(String text) {
        return courseRepository.findAllByNameContainingOrDescriptionContaining(text,text);
    }
}
