package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import org.springframework.http.ResponseEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Optional;

@Data
@Entity
public class Student {
    @Id
    private String username;
    private String password;
    private String name;
    private String surname;
//    @OneToMany(mappedBy = "student")
//    private List<Grade> gradeList;
    public Student() {
    }

    public Student(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }



}
