import axios from 'axios';

const SCORE_API_URL = 'http://localhost:8080/scores';

class ScoreService {
    getAllScores() {
        return axios.get(SCORE_API_URL);
    }

    getScoresByRUT(rut) {
        return axios.get(SCORE_API_URL + '/byRUT/' + rut);
    }

    deleteScoreById(scoreId) {
        return axios.delete(SCORE_API_URL + '/delete/' + scoreId);
    }

    deleteAllScores() {
        return axios.delete(SCORE_API_URL + '/deleteAll');
    }

    createScore(score) {
        return axios.post(SCORE_API_URL, score);
    }
}

export default new ScoreService();
