package TopEducation.adminOfficeservice.controllers;

import TopEducation.adminOfficeservice.models.StudentModel;
import TopEducation.adminOfficeservice.services.AdministrationOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/administrationOffice")
public class AdministrationOfficeController {

    @Autowired
    AdministrationOfficeService administrationOfficeService;

    // Update a student´s values
    @GetMapping("/update/{studentRUT}")
    public ResponseEntity<StudentModel> updateStudentByRUT(@PathVariable("studentRUT") String studentRUT) {
        StudentModel student = administrationOfficeService.findByRut(studentRUT);
        student = administrationOfficeService.updateStudentInfo(student.getRut());
        return ResponseEntity.ok(student);
    }

}