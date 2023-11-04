package TopEducation.file_managerservice.services;

import TopEducation.file_managerservice.models.StudentModel;
import lombok.Generated;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.logging.Logger;

@Service

public class FileManagerService {

    Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    RestTemplate restTemplate;

    // Send a student object to the student service
    @Generated
    public void sendStudentPostRequest(StudentModel model) {
        String otherServiceUrl = "http://localhost:8081/students";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<StudentModel> request = new HttpEntity<>(model, headers);

        restTemplate.postForEntity(otherServiceUrl, request, StudentModel.class);
    }

    // Save student data from an Excel file
    @Generated
    public void saveStudentDataFromExcel(MultipartFile file) throws IOException {

        logger.info("Saving student data from Excel file");

        // Verify if the Excel file is xlsx type
        if (Objects.requireNonNull(file.getOriginalFilename()).endsWith(".xlsx")) {
            logger.info("Excel file is xlsx type");

            try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
                Sheet sheet = workbook.getSheetAt(0);

                // Getting the students from the Excel file

                for (Row currentRow : sheet) {
                    if (currentRow.getRowNum() == 0) {
                        continue; // Skip header row
                    }

                    // Getting the student values from the Excel file
                    String rut = currentRow.getCell(0).getStringCellValue();

                    String firstName = currentRow.getCell(1).getStringCellValue();

                    String lastName = currentRow.getCell(2).getStringCellValue();

                    // Date format is dd-mm-yyyy
                    // The date is converted to LocalDate format
                    LocalDate birthDate = currentRow.getCell(3).getLocalDateTimeCellValue().toLocalDate();

                    String schoolName = currentRow.getCell(4).getStringCellValue();

                    String schoolType = currentRow.getCell(5).getStringCellValue();

                    // School type: 0 -> Municipal, 1 -> Subsidized, 2 -> Private
                    int schoolTypeInt;
                    switch (schoolType) {
                        case "Municipal" -> schoolTypeInt = 0;
                        case "Subvencionado" -> schoolTypeInt = 1;
                        case "Particular" -> schoolTypeInt = 2;
                        default -> throw new IllegalStateException("Unexpected value: " + schoolType);
                    }
                    int graduationYear = (int) currentRow.getCell(6).getNumericCellValue();

                    int agreedInstallments = (int) currentRow.getCell(7).getNumericCellValue();

                    // Create a new student object
                    StudentModel student = new StudentModel();

                    student.setRut(rut);
                    student.setFirstName(firstName);
                    student.setLastName(lastName);
                    student.setBirthDate(birthDate);
                    student.setSchoolType(schoolTypeInt);
                    student.setSchoolName(schoolName);
                    student.setGraduationYear(graduationYear);
                    student.setAgreedInstallments(agreedInstallments);

                    // Complete the student object with temporary values
                    student.setExamsTaken(0);
                    student.setAverageGrade(0);
                    student.setPaymentMethod("Cash");
                    student.setInstallmentsPaid(0);
                    student.setOverdueInstallments(0);
                    student.setLastPaymentDate(LocalDate.now());
                    student.setTotalAmountToPay(0);

                    System.out.println(student);

                    // Send the student object to the student service

                    // Attempt 1
                    restTemplate.postForObject("http://localhost:8081/students", student, StudentModel.class);

                    // Attempt 2
                    ResponseEntity<StudentModel> response = restTemplate.postForEntity("http://localhost:8081/students", student, StudentModel.class);

                    // Attempt 3
                    ResponseEntity<StudentModel> response2 = restTemplate.exchange(
                            "http://localhost:8080/students",
                            HttpMethod.POST,
                            new HttpEntity<>(student),
                            new ParameterizedTypeReference<StudentModel>() {}
                    );

                    // Attempt 4
                    sendStudentPostRequest(student);

                }

            } catch (Exception e) {
                // Handle exception
            }
        }

        logger.info("Student data saved successfully");
    }

}
