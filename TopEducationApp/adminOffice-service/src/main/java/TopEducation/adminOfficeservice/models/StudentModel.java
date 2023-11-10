package TopEducation.adminOfficeservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

// Part of the persistence layer
public class StudentModel {

    // RUT is a unique identifier for Chilean citizens
    private String rut;

    // First name
    private String firstName;

    // Last name
    private String lastName;

    // Birthdate
    private LocalDate birthDate;

    // School type: 0 -> Municipal, 1 -> Subsidized, 2 -> Private
    private int schoolType;

    // School name
    private String schoolName;

    // Year of graduation
    private int graduationYear;

    // Exams taken
    private int examsTaken;

    // Average grade in tests
    private int averageScore;

    // Payment method
    private String paymentMethod;

    // Number of agreed installments
    private int agreedInstallments;

    // Number of installments paid
    private int installmentsPaid;

    // Number of overdue installments
    private int overdueInstallments;

    // Last payment date
    private LocalDate lastPaymentDate;

    // Total amount to pay
    private int totalAmountToPay;

    // Total amount paid
    private int totalAmountPaid;
}