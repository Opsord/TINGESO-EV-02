package TopEducation.file_managerservice.controllers;

import TopEducation.file_managerservice.services.FileManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/files")

public class FileManagerController {

    @Autowired
    FileManagerService fileManagerService;

    @PostMapping("/uploadStudents")
    public String uploadStudentsExcel(@RequestParam("file") MultipartFile file) {
        try {
            // Save students data from Excel file
            fileManagerService.saveStudentsFromExcel(file);
            return "Students data saved successfully";
        } catch (IOException e) {
            // Handle the exception and return an error response
            return "Error saving students data";
        }
    }

    @PostMapping("/uploadScores")
    public String uploadScoresExcel(@RequestParam("file") MultipartFile file) {
        try {
            // Save scores data from Excel file
            fileManagerService.saveScoresFromExcel(file);
            return "Scores data saved successfully";
        } catch (IOException e) {
            // Handle the exception and return an error response
            return "Error saving scores data";
        }
    }

}