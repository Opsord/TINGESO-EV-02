package TopEducation.adminOfficeservice.controllers;

import TopEducation.adminOfficeservice.models.InstallmentModel;
import TopEducation.adminOfficeservice.models.ScoreModel;
import TopEducation.adminOfficeservice.models.StudentModel;
import TopEducation.adminOfficeservice.services.AdministrationOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/administrationOffice")
public class AdministrationOfficeController {

    @Autowired
    AdministrationOfficeService administrationOfficeService;

    // Get a student by RUT
    @GetMapping("/student/{studentRUT}")
    public ResponseEntity<StudentModel> findStudentByRUT(@PathVariable("studentRUT") String studentRUT) {
        StudentModel student = administrationOfficeService.findByRut(studentRUT);
        if (student == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(student);
        }
    }

    // Get all scores by RUT
    @GetMapping("/scores/{studentRUT}")
    public ResponseEntity<List<ScoreModel>> findScoresByRUT(@PathVariable("studentRUT") String studentRUT) {
        List<ScoreModel> scores = administrationOfficeService.getScoresByRUT(studentRUT);
        if (scores.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(scores);
        }
    }

    // Get all installments for a student by RUT
    @GetMapping("/installments/{studentRUT}")
    public ResponseEntity<List<InstallmentModel>> findInstallmentsByRUT(@PathVariable("studentRUT") String studentRUT) {
        List<InstallmentModel> installments = administrationOfficeService.getInstallmentsByRUT(studentRUT);
        if (installments.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(installments);
        }
    }

    // Get all paid installments for a student by RUT
    @GetMapping("/installments/paid/{studentRUT}")
    public ResponseEntity<List<InstallmentModel>> findPaidInstallmentsByRUT(@PathVariable("studentRUT") String studentRUT) {
        List<InstallmentModel> installments = administrationOfficeService.getPaidInstallmentsByRUT(studentRUT);
        if (installments.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(installments);
        }
    }

    // Get all overdue installments for a student by RUT
    @GetMapping("/installments/overdue/{studentRUT}")
    public ResponseEntity<List<InstallmentModel>> findOverdueInstallmentsByRUT(@PathVariable("studentRUT") String studentRUT) {
        List<InstallmentModel> installments = administrationOfficeService.getOverdueInstallmentsByRUT(studentRUT);
        if (installments.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(installments);
        }
    }

    // Update a student
    @GetMapping("/update/{studentRUT}")
    public ResponseEntity<String> updateStudent(@PathVariable("studentRUT") String studentRUT) {
        administrationOfficeService.updateStudentInfo(studentRUT);
        return ResponseEntity.ok("Student updated successfully");
    }
}