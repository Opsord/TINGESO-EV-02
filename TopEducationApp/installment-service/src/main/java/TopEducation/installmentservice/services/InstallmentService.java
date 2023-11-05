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
    public void deleteInstallment(Long id) {
        installmentRepository.deleteById(id);
    }

    // Delete all installments
    @Generated
    public void deleteAllInstallments() {
        installmentRepository.deleteAll();
    }

    // Find by methods

    // Find an installment by id
    public InstallmentEntity findInstallmentById(Long id) {
        return installmentRepository.findById(id).orElse(null);
    }

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
    public void updateInstallmentOverdueStatus(InstallmentEntity installment) {
        LocalDate dueDate = installment.getInstallmentDueDate();
        LocalDate currentDate = LocalDate.now();
        if (currentDate.isAfter(dueDate) && installment.getInstallmentStatus() == 0) {
            installment.setInstallmentOverdueStatus(1);
        } else {
            installment.setInstallmentOverdueStatus(0);
        }
    }

    // Update overdue status for installments by student RUT
    public void updateAllInstallmentsOverdueStatusByRUT(String installmentRUT) {
        List<InstallmentEntity> installments = findAllByInstallmentRUT(installmentRUT);
        for (InstallmentEntity installment : installments) {
            updateInstallmentOverdueStatus(installment);
        }
    }

    // Update interest for installments by student RUT
    public void updateOverduePenalty(String studentRUT) {
        // Get the overdue installments for the student
        List<InstallmentEntity> overdueInstallments = findAllOverdueInstallmentsByRUT(studentRUT);
        // If there are overdue installments, calculate the interest and update the installment amount
        if (overdueInstallments == null) {
            return;
        }
        // Update the overdue installments
        double interest = 0;
        // Calculate the interest
        interest = switch (overdueInstallments.size()) {
            case 1 -> 0.03;
            case 2 -> 0.06;
            case 3 -> 0.09;
            default -> 0.15;
        };
        // Update every installment overdue price
        List<InstallmentEntity> installments = findAllByInstallmentRUT(studentRUT);
        for (InstallmentEntity installment : installments) {
            installment.setInstallmentOverduePenalty((int) (installment.getInstallmentAmount() * interest));
            // Update the installment
            saveInstallment(installment);
        }
    }

    // Update installments by student RUT
    public void updateInstallmentsByRUT(String studentRUT) {
        updateAllInstallmentsOverdueStatusByRUT(studentRUT);
        updateOverduePenalty(studentRUT);
    }

    // Update installments related to a student from a determined installment
    public void updateInstallmentFromID(Long installmentID) {
        // Get the installment
        InstallmentEntity installment = findInstallmentById(installmentID);
        // Get the student RUT
        String studentRUT = installment.getInstallmentRUT();
        // Update the installments
        updateInstallmentsByRUT(studentRUT);
    }

    // Mark an installment as paid
    public void markInstallmentAsPAid(Long installmentID) {
        // Get the installment
        InstallmentEntity installment = findInstallmentById(installmentID);
        // Mark the installment as paid
        installment.setInstallmentStatus(1);
        // Set the overdue status to 0
        installment.setInstallmentOverdueStatus(0);
        // Set the payment date
        installment.setInstallmentPaymentDate(LocalDate.now());

        // Update the installment
        saveInstallment(installment);
    }
}

