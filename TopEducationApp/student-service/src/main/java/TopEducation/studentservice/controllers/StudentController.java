package TopEducation.studentservice.controllers;

import TopEducation.studentservice.entities.StudentEntity;
import TopEducation.studentservice.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")

public class StudentController {

    @Autowired
    StudentService studentService;

    // Get all the students
    @GetMapping
    public ResponseEntity<List<StudentEntity>> getAllStudents() {
        if (studentService.getAllStudents().isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(studentService.getAllStudents());
        }
    }

    // Get a student by ID
    @GetMapping("/{id}")
    public ResponseEntity<StudentEntity> getStudentById(@PathVariable("id") Long id) {
        if (studentService.findById(id) == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(studentService.findById(id));
        }
    }

    /*
    // Update a student by RUT
    @PutMapping("/update/byRUT/{studentRUT}")
    public ResponseEntity<StudentEntity> updateStudentByRUT(@PathVariable("studentRUT") String studentRUT, @RequestBody StudentEntity updatedStudent) {
        studentService.updateStudentByRUT(studentRUT, updatedStudent);
        return ResponseEntity.ok(updatedStudent);
    }
    */

    // Get a student by RUT
    @GetMapping("/byRUT/{studentRUT}")
    public ResponseEntity<StudentEntity> getStudentByRUT(@PathVariable("studentRUT") String studentRUT) {
        if (studentService.findByRut(studentRUT) == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(studentService.findByRut(studentRUT));
        }
    }

    // Save a student
    @PostMapping
    public ResponseEntity<StudentEntity> saveStudent(@RequestBody StudentEntity student) {
        System.out.println(student);
        studentService.saveStudent(student);
        return ResponseEntity.ok(student);
    }

    // Delete a student by id
    @GetMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student deleted successfully");
    }

    // Delete all students
    @GetMapping("/deleteAll")
    public ResponseEntity<String> deleteAllStudents() {
        studentService.deleteAllStudents();
        return ResponseEntity.ok("All students deleted successfully");
    }
}
