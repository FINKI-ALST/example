package mk.ukim.finki.wp.lab.model.exceptions;

public class StudentAlreadyExists extends RuntimeException{
    public StudentAlreadyExists(String username) {
        super(String.format("Student with username: %s,already exists",username));
    }
}
