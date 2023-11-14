package TopEducation.file_managerservice.services;

import TopEducation.file_managerservice.models.ScoreModel;
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

    // Save student data from an Excel file
    @Generated
    public void saveStudentsFromExcel(MultipartFile file) throws IOException {

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

                    if (currentRow.getCell(0) == null) {
                        break; // Stop reading the file
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
                        case "Privado" -> schoolTypeInt = 2;
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
                    student.setExamsTaken(5);
                    student.setAverageGrade(0);
                    student.setPaymentMethod("Cash");
                    student.setInstallmentsPaid(0);
                    student.setOverdueInstallments(0);
                    student.setLastPaymentDate(LocalDate.now());
                    student.setTotalAmountToPay(0);

                    // Send the student object to the student service
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);

                    HttpEntity<StudentModel> request = new HttpEntity<>(student, headers);
                    ResponseEntity<StudentModel> response = restTemplate.exchange(
                            "http://gateway-service:8080/students",
                            HttpMethod.POST,
                            request,
                            new ParameterizedTypeReference<>() {
                            });
                    if (response.getStatusCode() == HttpStatus.OK) {
                        logger.info("Data for student " + rut + " saved successfully");
                    } else {
                        logger.info("Error saving student data");
                    }
                }
            } catch (Exception e) {
                logger.info(e.toString());
            }
        }

        logger.info("Student data saved successfully");
    }

    // Save score data from an Excel file
    @Generated
    public void saveScoresFromExcel(MultipartFile file) throws IOException {
        logger.info("Saving score data from Excel file");

        // Verify if the Excel file is xlsx type
        if (Objects.requireNonNull(file.getOriginalFilename()).endsWith(".xlsx")) {
            logger.info("Excel file is xlsx type");

            try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
                // Iterate through the sheets
                for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                    Sheet sheet = workbook.getSheetAt(i);

                    // Iterate through the rows
                    for (Row currentRow : sheet) {
                        if (currentRow.getRowNum() == 0) {
                            continue; // Skip header row
                        }

                        if (currentRow.getCell(0) == null) {
                            break; // Stop reading the file
                        }

                        // Getting the score values from the Excel file
                        String scoreRUT = currentRow.getCell(0).getStringCellValue();

                        int score = (int) currentRow.getCell(1).getNumericCellValue();

                        LocalDate examDate = currentRow.getCell(2).getLocalDateTimeCellValue().toLocalDate();

                        // Create a new score object
                        ScoreModel scoreModel = new ScoreModel();

                        scoreModel.setScoreRUT(scoreRUT);
                        scoreModel.setScore(score);
                        scoreModel.setExamDate(examDate);

                        // Send the score object to the student service
                        HttpHeaders headers = new HttpHeaders();
                        headers.setContentType(MediaType.APPLICATION_JSON);

                        HttpEntity<ScoreModel> request = new HttpEntity<>(scoreModel, headers);
                        ResponseEntity<ScoreModel> response = restTemplate.exchange(
                                "http://gateway-service:8080/scores",
                                HttpMethod.POST,
                                request,
                                new ParameterizedTypeReference<>() {
                                });
                        if (response.getStatusCode() == HttpStatus.OK)
                            logger.info("Score for rut " + scoreRUT + " saved successfully");
                        else {
                            logger.info("Error saving score");
                        }
                    }
                }
            } catch (Exception e) {
                logger.info(e.toString());
            }
        }

        logger.info("Score data saved successfully");
    }


}
