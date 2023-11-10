import axios from 'axios';

const FILE_API_URL = 'http://localhost:8080/files';

class FileManagerService {
    uploadStudensFile(file) {
        return axios.post(FILE_API_URL + '/uploadStudents', file);
    }

    uploadScoresFile(file) {
        return axios.post(FILE_API_URL + '/uploadScores', file);
    }
}

export default new FileManagerService();
