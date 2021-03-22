package mk.ukim.finki.wp.lab.bootstrap;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Student> students=new ArrayList<Student>();
    public static List<Course> courses=new ArrayList<Course>();
    public static List<Student> studentsVP=new ArrayList<Student>();
    public static List<Student> studentsKE=new ArrayList<Student>();
    public static List<Student> studentsNICK=new ArrayList<Student>();
    public static List<Student> studentsMP=new ArrayList<Student>();
    public static List<Student> studentsV=new ArrayList<Student>();
   // public static List<Teacher> teachers=new ArrayList<>();

//    @PostConstruct
//    public void init()
//    {
//        Teacher teacher= TeacherRepository.teachers.get(1);
//    0    students.add(new Student("stefanstefanovski","ss","Stefan","Stefanovski"));
//    1   students.add(new Student("atanasgeorgiev","ag","Atanas","Georgiev"));
//    2    students.add(new Student("milicajovanova","mj","Milica","Jovanova"));
//    3    students.add(new Student("marioshumenkovski","ms","Mario","Shumenkovski"));
//    4    students.add(new Student("vaskojovov","vj","Vasko","Jovov"));
//
//        studentsVP.add(students.get(0));
//        studentsVP.add(students.get(2));
//        studentsVP.add(students.get(4));
//        courses.add(new Course("Veb programiranje","Izucuvanje Spring Boot",studentsVP,teacher, TypeStatus.SUMMER));
//
//        studentsKE.add(students.get(1));
//        studentsKE.add(students.get(2));
//        studentsKE.add(students.get(3));
//        courses.add(new Course("Kompjuterska Etika","Principi na eticko odnesuvanje pri koristenje na internetot",studentsKE,teacher,TypeStatus.SUMMER));
//
//        studentsNICK.add(students.get(3));
//        studentsNICK.add(students.get(2));
//        courses.add(new Course("Napredna interakcija covek kompjuter","Aplikacii za lugje so odreden hendikep",studentsNICK,teacher,TypeStatus.MANDATORY));
//
//        studentsV.add(students.get(4));
//        courses.add(new Course("Vizuelizacija","Vizueliziranje grafici",studentsV,teacher,TypeStatus.WINTER));
//
//        studentsMP.add(students.get(0));
//        studentsMP.add(students.get(2));
//        courses.add(new Course("Mobilni platformi i programiranje","Izrabotka na Android aplikacija",studentsMP,teacher,TypeStatus.ELECTIVE));
//
//    }
}
