package TopEducation.studentservice.services;

import TopEducation.studentservice.entities.StudentEntity;
import TopEducation.studentservice.repositories.StudentRepository;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

// Part of the business layer
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Save a student
    public void saveStudent(StudentEntity student) {
        studentRepository.save(student);
    }

    // Get all the students
    @Generated
    public List<StudentEntity> getAllStudents() {
        return (List<StudentEntity>) studentRepository.findAll();
    }

    // Delete a student
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    // Delete all students
    @Generated
    public void deleteAllStudents() {
        studentRepository.deleteAll();
    }

    // Find by methods

    // Find by ID
    public StudentEntity findById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    // Find by RUT
    public StudentEntity findByRut(String rut) {
        return studentRepository.findByRut(rut);
    }


}
