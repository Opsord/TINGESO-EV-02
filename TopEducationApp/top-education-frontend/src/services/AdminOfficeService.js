import axios from 'axios';

const ADMIN_API_URL = 'http://localhost:8080/administrationOffice';

class AdminOfficeService {
    updateStudentInfo(student) {
        return axios.get(ADMIN_API_URL + '/update/', student);
    }
}

export default new AdminOfficeService();
