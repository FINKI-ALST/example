package mk.ukim.finki.wp.lab.model.exceptions;

public class CourseNotFound extends RuntimeException {
    public CourseNotFound() {
        super("Course Not Found");
    }
}
