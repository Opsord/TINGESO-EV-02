package TopEducation.installmentservice.services;

import TopEducation.installmentservice.entities.InstallmentEntity;
import TopEducation.installmentservice.repositories.InstallmentRepository;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service

public class InstallmentService {

    @Autowired
    private InstallmentRepository installmentRepository;

    // Save an installment
    public void saveInstallment(InstallmentEntity installment) {
        installmentRepository.save(installment);
    }

    // Delete an installment
    public void deleteInstallment(Long id) throws Exception {
        installmentRepository.deleteById(id);
    }

    // Delete all installments
    @Generated
    public void deleteAllInstallments() throws Exception {
        installmentRepository.deleteAll();
    }

    // Find by methods

    // Find all installments
    public List<InstallmentEntity> getAllInstallments() {
        return (List<InstallmentEntity>) installmentRepository.findAll();
    }

    // Find all installments by student RUT
    public List<InstallmentEntity> findAllByInstallmentRUT(String installmentRUT) {
        return (installmentRepository.findAllInstallmentsByRUT(installmentRUT));
    }

    // Find all paid installments by student RUT
    public List<InstallmentEntity> findAllPaidInstallmentsByRUT(String installmentRUT) {
        return (installmentRepository.findAllPaidInstallmentsByRUT(installmentRUT));
    }

    // Find all overdue installments by student RUT
    public List<InstallmentEntity> findAllOverdueInstallmentsByRUT(String installmentRUT) {
        return (installmentRepository.findAllOverdueInstallmentsByRUT(installmentRUT));
    }

    // Verify is an installment is overdue
    public boolean updateInstallmentOverdueStatus(InstallmentEntity installment) {
        LocalDate paymentDate = installment.getInstallmentPaymentDate();
        LocalDate currentDate = LocalDate.now();
        if (currentDate.isAfter(paymentDate.plusMonths(1)) && installment.getInstallmentStatus() == 0) {
            installment.setInstallmentOverdueStatus(1);
            return true;
        }
        installment.setInstallmentOverdueStatus(0);
        return false;
    }

    // Mark an installment as paid
    public void markInstallmentAsPAid(InstallmentEntity installment) {
        installment.setInstallmentStatus(1);
    }
}

