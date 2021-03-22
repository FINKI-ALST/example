package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.exceptions.CourseNotFound;
import mk.ukim.finki.wp.lab.model.exceptions.UserNotFound;
import mk.ukim.finki.wp.lab.repository.jpa.CourseRepository;
import mk.ukim.finki.wp.lab.repository.jpa.GradeRepository;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepository;
import mk.ukim.finki.wp.lab.service.GradeService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GradeServiceImpl implements GradeService{
    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public GradeServiceImpl(GradeRepository gradeRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.gradeRepository = gradeRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Grade>  findGradesOfCourse(Long courseId) {
        Course course= courseRepository.findById(courseId).orElseThrow(()-> new CourseNotFound());
        return gradeRepository.findAllByCourse(course);
    }

    @Transactional
    @Override
    public Grade addStudentInCourseWithGrade(String username, Long courseId, Character grade) {
        Student student1=studentRepository.findByUsername(username).orElseThrow(()-> new UserNotFound());
        Course course1=courseRepository.findById(courseId).orElseThrow(()->new CourseNotFound());
        gradeRepository.deleteByStudent(student1);
        Grade grade1=new Grade(grade,student1,course1);
        return gradeRepository.save(grade1);
    }
}
