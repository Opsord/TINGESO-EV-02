import axios from 'axios';

const ADMIN_API_URL = 'http://localhost:8080/administrationOffice';

class AdminOfficeService {
    updateStudentInfo(studentRUT) {
        return axios.get(ADMIN_API_URL + '/update/' + studentRUT);
    }
}

export default new AdminOfficeService();
