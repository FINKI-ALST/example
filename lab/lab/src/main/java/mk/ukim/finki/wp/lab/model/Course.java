package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import mk.ukim.finki.wp.lab.model.enumeration.TypeStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Course implements Comparable<Course>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    private String name;
    private String description;
    @ManyToMany
    private List<Student> students;
    @ManyToOne
    private Teacher teacher;
    @Enumerated(EnumType.STRING)
    private TypeStatus typeStatus;

    public Course() {
    }

    public Course(Long courseId, String name, String description, List<Student> students, Teacher teacher, TypeStatus typeStatus) {
        this.courseId = courseId;
        this.name = name;
        this.description = description;
        this.students = students;
        this.teacher=teacher;
        this.typeStatus=typeStatus;
    }

    public Course(String name, String description, List<Student> students,Teacher teacher,TypeStatus typeStatus) {
        this.name = name;
        this.description = description;
        this.students = students;
        this.teacher=teacher;
        this.typeStatus=typeStatus;
    }
    public Course(String name,String description,Teacher teacher,TypeStatus typeStatus)
    {
        this.name = name;
        this.description = description;
        this.students = new ArrayList<>();
        this.teacher=teacher;
        this.typeStatus=typeStatus;
    }



    public TypeStatus getTypeStatus() {
        return typeStatus;
    }

//    public Course(String name, String description, List<Student> students, Teacher teacher) {
//        this.name = name;
//        this.description = description;
//        this.students = students;
//        this.teacher=teacher;
//    }
//    public Course(String name,String description,Teacher teacher)
//    {
//        this.courseId = (long) (Math.random() * 1000);
//        this.name = name;
//        this.description = description;
//        this.students = new ArrayList<>();
//        this.teacher=teacher;
//    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Long getCourseId() {
        return courseId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Student> getStudents() {
        return students;
    }


    @Override
    public int compareTo(Course o) {
        return this.name.compareTo(o.name);
    }
}
