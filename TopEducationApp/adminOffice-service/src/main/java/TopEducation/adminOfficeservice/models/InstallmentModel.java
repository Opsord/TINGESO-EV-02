package TopEducation.adminOfficeservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

// Part of the persistence layer
public class InstallmentModel {

    // RUT associated with the installment
    private String installmentRUT;

    // Installment amount
    private int installmentAmount;

    // Installment status: 0 -> Pending, 1 -> Paid
    private int installmentStatus;

    // Installment due date
    private LocalDate installmentDueDate;

    // Installment payment date
    private LocalDate installmentPaymentDate;

    // Installment overdue status: 0 -> Not overdue, 1 -> Overdue
    private int installmentOverdueStatus;

    // Installment overdue price
    private int installmentOverduePenalty;
}