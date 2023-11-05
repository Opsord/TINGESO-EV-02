package TopEducation.administration_officeservice.controllers;

import TopEducation.administration_officeservice.models.ScoreModel;
import TopEducation.administration_officeservice.models.StudentModel;
import TopEducation.administration_officeservice.services.AdministrationOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @RequestMapping("/{studentRUT}")
    public ResponseEntity<StudentModel> findStudentByRUT(@PathVariable("studentRUT") String studentRUT) {
        StudentModel student = administrationOfficeService.getStudentByRUT(studentRUT);
        System.out.println(student);
        if (student == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(student);
        }
    }

    // Get all scores by RUT
    @RequestMapping("/scores/{studentRUT}")
    public ResponseEntity<List<ScoreModel>> findScoresByRUT(@PathVariable("studentRUT") String studentRUT) {
        List<ScoreModel> scores = administrationOfficeService.getScoresByRUT(studentRUT);
        if (scores.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(scores);
        }
    }
}