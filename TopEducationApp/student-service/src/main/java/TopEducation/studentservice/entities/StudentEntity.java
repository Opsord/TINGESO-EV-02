package TopEducation.studentservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor

// Part of the persistence layer
public class StudentEntity {

    @Id
    @NotNull

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
