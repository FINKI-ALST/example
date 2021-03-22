package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryTeacherRepository {
    public static List<Teacher> teachers=new ArrayList<>();
    @PostConstruct
    public void init()
    {
        teachers.add(new Teacher("Marko","Markov"));
        teachers.add(new Teacher("Nikola","Nikolov"));
        teachers.add(new Teacher("Elena","Jovanova"));
        teachers.add(new Teacher("Aleksandra","Stefanovska"));
        teachers.add(new Teacher("Angela","Jovanoska"));
    }
    public List<Teacher> findAll()
    {
        return teachers;
    }
    public Optional<Teacher> findTeacherById(Long id)
    {
        return teachers.stream().filter(i->i.getId().equals(id)).findFirst();
    }

}
