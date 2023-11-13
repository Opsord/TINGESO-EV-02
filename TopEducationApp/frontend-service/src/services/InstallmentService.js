import axios from 'axios';

const INSTALLMENT_API_URL = 'http://localhost:8080/installments';

class InstallmentService {
    getAllInstallments() {
        return axios.get(INSTALLMENT_API_URL);
    }

    getInstallmentById(installmentId) {
        return axios.get(INSTALLMENT_API_URL + '/' + installmentId);
    }

    getInstallmentsByRUT(rut) {
        return axios.get(INSTALLMENT_API_URL + '/byRUT/' + rut);
    }

    deleteInstallment(installmentId) {
        return axios.delete(INSTALLMENT_API_URL + '/delete/' + installmentId);
    }

    deleteInstallmentsByRUT(rut) {
        return axios.delete(INSTALLMENT_API_URL + '/deleteAllByRUT/' + rut);
    }

    markInstallmentAsPaid(installmentId) {
        return axios.put(INSTALLMENT_API_URL + '/pay/' + installmentId);
    }
}

export default new InstallmentService();
