import axios from '../custom-axios/axios';

const CourseStudentService = {
    fetchStudents: () => {
        return axios.get("/students");
    },
    /*fetchCourses: () => {
        return axios.get("/courses");
    },
    fetchTeachers: () => {
        return axios.get("/teachers");
    },*/
}

export default CourseStudentService;

