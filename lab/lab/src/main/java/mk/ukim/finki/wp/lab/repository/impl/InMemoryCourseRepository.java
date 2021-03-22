package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.enumeration.TypeStatus;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryCourseRepository {
    public List<Course> findAllCourses()
    {
         Collections.sort(DataHolder.courses);
         return DataHolder.courses;
    }
    public Optional<Course> findById(Long courseId)
    {
        return DataHolder.courses.stream().filter(r->r.getCourseId().equals(courseId)).findFirst();
    }
    public  List<Student> findAllStudentsByCourse(Long courseId)
    {
        return DataHolder.courses.stream().filter(r->r.getCourseId().equals(courseId)).findFirst().get().getStudents();
    }
    public Course addStudentToCourse(Student student, Course course)
    {
        course.getStudents().removeIf(r->r.getUsername().equals(student.getUsername()));
        course.getStudents().add(student);
        return course;
    }
    public Optional<Course> findCourseByName(String name)
    {
        return DataHolder.courses.stream().filter(i->i.getName().equals(name)).findFirst();
    }
    public List<Course> findCoursesByType(String type)
    {
        return DataHolder.courses.stream().filter(i->i.getTypeStatus().toString().equals(type)).collect(Collectors.toList());
    }
    public Optional<Course> save(String name, String description, Teacher teacher,String typestatus)
    {
//        Course course=null;
//        if(DataHolder.courses.stream().filter(i->i.getName().equals(name)).findFirst().isPresent())
//        {
//            List<Student> students=DataHolder.courses.stream().filter(i->i.getName().equals(name)).findFirst().get().getStudents();
//            DataHolder.courses.removeIf(i->i.getName().equals(name));
//             course=new Course(name,description,students,teacher);
//        }
//        else
//        {
//             course=new Course(name,description,teacher);
//        }
//
        TypeStatus typeStatus=TypeStatus.valueOf(typestatus);
       Course course=new Course(name,description,teacher,typeStatus);
       DataHolder.courses.add(course);
       return Optional.of(course);
    }
    public Optional<Course> update(Long courseId,String name,String description,Teacher teacher,String typestatus)
    {
        List<Student> students=this.findById(courseId).get().getStudents();
        DataHolder.courses.removeIf(i->i.getCourseId().equals(courseId));
        TypeStatus typeStatus=TypeStatus.valueOf(typestatus);
        Course course=new Course(courseId,name,description,students,teacher,typeStatus);
        DataHolder.courses.add(course);
        return Optional.of(course);
    }
    public void deleteById(Long id)
    {
        DataHolder.courses.removeIf(i->i.getCourseId().equals(id));
    }


}
