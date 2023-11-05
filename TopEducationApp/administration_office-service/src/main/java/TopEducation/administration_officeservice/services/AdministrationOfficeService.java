package TopEducation.administration_officeservice.services;

import TopEducation.administration_officeservice.models.InstallmentModel;
import TopEducation.administration_officeservice.models.ScoreModel;
import TopEducation.administration_officeservice.models.StudentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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



    // Communication with other microservices

    // Get a student by RUT
    public StudentModel getStudentByRUT(String rut) {
        String url = "http://localhost:8080/students/" + rut;
        try {
            ResponseEntity<StudentModel> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, StudentModel.class);
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                return responseEntity.getBody();
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.info("Error: " + e.getMessage());
            return null;
        }
    }

    // Get all scores by RUT
    public List<ScoreModel> getScoresByRUT(String rut) {
        return restTemplate.getForObject("http://localhost:8080/scores/byRUT/" + rut, List.class);
    }

    // Get all installments by RUT
    public List<InstallmentModel> getInstallmentsByRUT(String rut) {
        return restTemplate.getForObject("http://localhost:8080/installments/byRUT/" + rut, List.class);
    }

    // Get all paid installments by RUT
    public List<InstallmentModel> getPaidInstallmentsByRUT(String rut) {
        return restTemplate.getForObject("http://localhost:8080/installments/byRUT/paid/" + rut, List.class);
    }

    // Get all overdue installments by RUT
    public List<InstallmentModel> getOverdueInstallmentsByRUT(String rut) {
        return restTemplate.getForObject("http://localhost:8080/installments/byRUT/overdue/" + rut, List.class);
    }

    // Testing
    // Get and print a student by RUT
    public String getAndPrintStudentByRUT(String rut) {
        StudentModel student = getStudentByRUT(rut);
        return rut;
    }


    // Constants

    // Enrollment cost -> 70.000 CLP
    private final int enrollmentCost = 70000;
    // Annual duty -> 1.500.000 CLP
    private final int annualDuty = 1500000;



    // Cash payment method calculations

    // Calculate the annual cost if the payment is made in cash
    public double calculateAnnualCostCash(StudentModel student) {
        return (enrollmentCost + annualDuty) * 0.5;
    }


    // Installments payment method calculations

    // Calculate the maximum number of installments
    public int calculateMaxInstallments(StudentModel student) {
        // Getting the school type of the student
        int schoolType = student.getSchoolType();
        int maxInstallments = 0;
        // School type: 0 -> Municipal, 1 -> Subsidized, 2 -> Private
        if (schoolType == 0) {
            maxInstallments = 10;
        } else if (schoolType == 1) {
            maxInstallments = 7;
        } else if (schoolType == 2) {
            maxInstallments = 4;
        }
        return maxInstallments;
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
        int averageScore = student.getAverageGrade();
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

}
