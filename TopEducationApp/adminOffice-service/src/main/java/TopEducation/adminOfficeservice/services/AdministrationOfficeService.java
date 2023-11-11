package TopEducation.adminOfficeservice.services;

import TopEducation.adminOfficeservice.models.InstallmentModel;
import TopEducation.adminOfficeservice.models.ScoreModel;
import TopEducation.adminOfficeservice.models.StudentModel;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

@Service
public class AdministrationOfficeService {

    Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    RestTemplate restTemplate;


    // Links

    // Get all students
    String getAllStudentsURL = "http://localhost:8080/students";

    // Get a student by RUT
    String getStudentByRUTURL = "http://localhost:8080/students/byRUT/";


    // Communication with other microservices

    // Get a student by RUT
    public StudentModel findByRut(String rut) {
        ResponseEntity<StudentModel> response = restTemplate.exchange(
                "http://localhost:8080/students/byRUT/" + rut,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<StudentModel>() {
                }
        );
        return response.getBody();
    }

    // Update a student
    public StudentModel updateStudentValues(StudentModel newStudent) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<StudentModel> request = new HttpEntity<>(newStudent, headers);
        ResponseEntity<StudentModel> response = restTemplate.exchange(
                "http://localhost:8080/students",
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<StudentModel>() {}
        );
        return response.getBody();
    }

    // Get all scores by RUT
    public List<ScoreModel> getScoresByRUT(String rut) {
        ResponseEntity<List<ScoreModel>> response = restTemplate.exchange(
                "http://localhost:8080/scores/byRUT/" + rut,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ScoreModel>>() {
                }
        );
        return response.getBody();
    }

    // Get all installments by RUT
    public List<InstallmentModel> getInstallmentsByRUT(String rut) {
        ResponseEntity<List<InstallmentModel>> response = restTemplate.exchange(
                "http://localhost:8080/installments/byRUT/" + rut,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<InstallmentModel>>() {
                }
        );
        return response.getBody();
    }

    // Get all paid installments by RUT
    public List<InstallmentModel> getPaidInstallmentsByRUT(String rut) {
        ResponseEntity<List<InstallmentModel>> response = restTemplate.exchange(
                "http://localhost:8080/installments/byRUT/paid/" + rut,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<InstallmentModel>>() {
                }
        );
        return response.getBody();
    }

    // Get all overdue installments by RUT
    public List<InstallmentModel> getOverdueInstallmentsByRUT(String rut) {
        ResponseEntity<List<InstallmentModel>> response = restTemplate.exchange(
                "http://localhost:8080/installments/byRUT/overdue/" + rut,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<InstallmentModel>>() {
                }
        );
        return response.getBody();
    }



    // Update student academic information (average score and exams taken) by RUT
    public StudentModel updateStudentAcademicInfo(StudentModel student) {
        // Getting the scores
        List<ScoreModel> scores = getScoresByRUT(student.getRut());
        // Calculating the average score
        int averageScore = 0;
        for (ScoreModel score : scores) {
            averageScore += score.getScore();
        }
        averageScore = averageScore / scores.size();
        // Update the student´s exams taken
        student.setExamsTaken(scores.size());
        // Updating the student average score
        student.setAverageScore(averageScore);
        // Saving the student
        logger.info(scores.size() + " scores found for RUT: " + student.getRut());
        logger.info("Average score calculated: " + averageScore);
        return student;
    }






    // Constants

    // Enrollment cost -> 70.000 CLP
    private final int enrollmentCost = 70000;
    // Annual duty -> 1.500.000 CLP
    private final int annualDuty = 1500000;



    // Cash payment method calculations

    // Calculate the annual cost if the payment is made in cash
    public double calculateAnnualCostCash() {
        return (enrollmentCost + annualDuty) * 0.5;
    }

    // Discount calculations

    // Calculate the discount depending on the type of school
    public double calculateSchoolTypeDiscount(StudentModel student) {
        if (student.getSchoolType() == 0) {
            return 0.2;
        } else if (student.getSchoolType() == 1) {
            return 0.1;
        } else if (student.getSchoolType() == 2) {
            return 0;
        } else {
            return 0;
        }
    }

    // Calculate the discount depending on the years since graduation
    public double calculateTimeAfterGraduationDiscount(StudentModel student) {
        // Getting the current date
        LocalDate currentYear = LocalDate.now();
        // Calculating how many years have passed since the student graduated
        int yearsSinceGraduation = currentYear.getYear() - student.getGraduationYear();
        // First range: 0-1 year
        if (yearsSinceGraduation <= 1) {
            return 0.15;
            // Second range: 1-2 years
        } else if (yearsSinceGraduation <= 3) {
            return 0.08;
            // Third range: 3-4 years
        } else if (yearsSinceGraduation <= 5) {
            return 0.04;
            // Fourth range: 5+ years
        } else {
            return 0;
        }
    }

    // Calculate the discount depending on the average score
    public double calculateAverageScoreDiscount(StudentModel student) {
        // Getting the average score of the student
        int averageScore = student.getAverageScore();
        // First range: 950 – 1000
        if (averageScore >= 950) {
            return 0.1;
            // Second range: 900 – 949
        } else if (averageScore >= 900) {
            return 0.05;
            // Third range: 850 – 899
        } else if (averageScore >= 850) {
            return 0.02;
            // Fourth range: < 850
        } else {
            return 0;
        }
    }

    // Calculate the annual cost if the payment is made in installments
    public double calculateAnnualCostInstallments(StudentModel student) {
        // Getting the annual cost
        int annualCost = enrollmentCost + annualDuty;
        // Getting the discounts
        double schoolTypeDis = calculateSchoolTypeDiscount(student);
        double timeAfterGraduationDis = calculateTimeAfterGraduationDiscount(student);
        double averageScoreDis = calculateAverageScoreDiscount(student);
        // Calculating the final price
        return (int) (annualCost * (1 - schoolTypeDis - timeAfterGraduationDis - averageScoreDis));
    }

    // Calculate the total amount to pay depending on if the payment is made in cash or installments
    public int calculateAnnualCost(StudentModel student) {
        if (student.getAgreedInstallments() == 1) {
            return (int) calculateAnnualCostCash();
        } else {
            return (int) calculateAnnualCostInstallments(student);
        }
    }

    // Update last payment date
    public void updateLastPaymentDate(StudentModel student) {
        // Get the paid installments of the student
        List<InstallmentModel> paidInstallments = getPaidInstallmentsByRUT(student.getRut());
        if (paidInstallments == null) {
            student.setLastPaymentDate(null);
        } else {
            // Get the payment date of the closest installment to the current date
            LocalDate latestPaymentDate = LocalDate.of(LocalDate.now().getYear(), 1, 5);
            for (InstallmentModel installment : paidInstallments) {
                if (installment.getInstallmentPaymentDate().isAfter(latestPaymentDate)) {
                    latestPaymentDate = installment.getInstallmentPaymentDate();
                }
            }
            // Update the last payment date
            student.setLastPaymentDate(latestPaymentDate);
        }
    }

    // Check if a student has missing installments and generate the missing ones
    public void checkMissingInstallments(String studentRUT) {
        // Get the student
        StudentModel student = findByRut(studentRUT);
        // Get the number of installments agreed by the student
        int agreedInstallments = student.getAgreedInstallments();
        // Get a list of the installments that match the RUT of the student
        List<InstallmentModel> installments = getInstallmentsByRUT(studentRUT);
        // Found installments
        int foundInstallments = 0;
        if (installments == null) {
            logger.info("No installments found for RUT: " + student.getRut());
        } else {
            foundInstallments = installments.size();
            logger.info("Found " + foundInstallments + " installments for the RUT: " + student.getRut());
        }
        // Calculate the number of installments that are missing
        int missingInstallments = agreedInstallments - foundInstallments;

        // Verify if there are missing installments
        if (missingInstallments > 0) {
            logger.info("Generating " + missingInstallments + " missing installments for RUT: " + student.getRut());
            // Generate the missing installments
            for (int i = 0; i < missingInstallments; i++) {
                // Create a new installment
                InstallmentModel installment = new InstallmentModel();
                // Setting the values of the installment

                // Set the RUT of the installment
                installment.setInstallmentRUT(student.getRut());
                // Set the amount of the installment
                installment.setInstallmentAmount (calculateAnnualCost(student) / agreedInstallments);
                // Set the status - Installment status: 0 -> Pending, 1 -> Paid
                installment.setInstallmentStatus(0);
                // Set the due date of the installment (starting from the 5th on january)
                LocalDate startDate = LocalDate.of(LocalDate.now().getYear(), 1, 5);
                installment.setInstallmentDueDate(startDate.plusMonths(i));
                // Set the payment date of the installment to null
                installment.setInstallmentPaymentDate(null);
                // Set the overdue status - Installment overdue status: 0 -> Not overdue, 1 -> Overdue
                installment.setInstallmentOverdueStatus(0);
                // Set the overdue penalty
                installment.setInstallmentOverduePenalty(0);

                // Save the installment
                restTemplate.postForObject("http://localhost:8080/installments", installment, InstallmentModel.class);
            }
        }

    }

    // Update student economic information
    // (total amount paid, total amount to pay, installments paid, payment method) by RUT
    public StudentModel updateStudentEconomicInfo(StudentModel student) {

        // Get a list of the installments that match the RUT of the student
        List<InstallmentModel> installments = getInstallmentsByRUT(student.getRut());

        // Calculate the total amount paid and paid installments
        int totalAmountPaid = 0;
        int paidInstallments = 0;
        for (InstallmentModel installment : installments) {
            if (installment.getInstallmentStatus() == 1) {
                totalAmountPaid += (installment.getInstallmentAmount() + installment.getInstallmentOverduePenalty());
                paidInstallments++;
            }
        }
        // Update the total amount paid and paid installments
        student.setTotalAmountPaid(totalAmountPaid);
        student.setInstallmentsPaid(paidInstallments);

        // Calculate the total amount to pay
        int totalAmountToPay = 0;
        for (InstallmentModel installment : installments) {
            totalAmountToPay += (installment.getInstallmentAmount() + installment.getInstallmentOverduePenalty());
        }
        // Update the total amount to pay
        student.setTotalAmountToPay(totalAmountToPay);

        // Update the last payment date
        updateLastPaymentDate(student);

        // Update overdue installments number
        List<InstallmentModel> overdueInstallments = getOverdueInstallmentsByRUT(student.getRut());
        if (overdueInstallments == null) {
            student.setOverdueInstallments(0);
        } else {
            student.setOverdueInstallments(overdueInstallments.size());
        }


        // Update payment method (more efficient if it is done here)
        if (student.getAgreedInstallments() == 1) {
            student.setPaymentMethod("Cash");
        } else {
            student.setPaymentMethod("Installments");
        }
        return student;
    }

    // Update student information by RUT
    @Generated
    public StudentModel updateStudentInfo(String studentRUT) {
        // Get the student
        StudentModel student = findByRut(studentRUT);
        // Update the academic info
        student = updateStudentAcademicInfo(student);
        logger.info("Academic info updated for RUT: " + studentRUT);
        // Check for missing installments
        checkMissingInstallments(studentRUT);
        logger.info("Missing installments checked for RUT: " + studentRUT);
        // Update the economic info
        student = updateStudentEconomicInfo(student);
        logger.info("Economic info updated for RUT: " + studentRUT);

        // Por Marci
        System.out.println("holi, quiero pasajes para un crucero en el caribe");

        return updateStudentValues(student);
    }
}
