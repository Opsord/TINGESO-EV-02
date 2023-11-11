import axios from 'axios';

const STUDENT_API_URL = 'http://localhost:8080/students';

class StudentService {
    getAllStudents() {
        return axios.get(STUDENT_API_URL);
    }

    getStudentByRut(studentRut) {
        return axios.get(STUDENT_API_URL + '/byRUT/' + studentRut);
    }

    saveStudent(student) {
        return axios.post(STUDENT_API_URL, student);
    }

    deleteStudent(studentRut) {
        return axios.delete(STUDENT_API_URL + '/delete/' + studentRut);
    }

    deleteAllStudents() {
        return axios.delete(STUDENT_API_URL + '/deleteAll');
    }
}

export default new StudentService();
