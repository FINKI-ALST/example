package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Grade;

import java.util.List;

public interface GradeService {
    List<Grade>  findGradesOfCourse(Long courseId);
    Grade addStudentInCourseWithGrade(String username, Long courseId,Character grade);

}
