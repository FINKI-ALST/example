package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
public class InMemoryStudentRepository {
    public List<Student> findAllStudents()
    {
       return DataHolder.students;
    }
    public List<Student> findAllByNameOrSurname(String text)
    {
        return DataHolder.students.stream().filter(r->r.getName().contains(text) || r.getSurname().contains(text)).collect(Collectors.toList());
    }
    public Optional<Student> findByUsername(String text)
    {
        return DataHolder.students.stream().filter(r->r.getUsername().equals(text)).findFirst();
    }

    public Student save(Student s)
    {
        DataHolder.students.removeIf(r->r.getUsername().equals(s.getUsername()));
        DataHolder.students.add(s);
        return s;
    }
}
