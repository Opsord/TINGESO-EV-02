package TopEducation.adminOfficeservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

// Part of the persistence layer
public class ScoreModel {

    // RUT is a unique identifier for Chilean citizens
    private String scoreRUT;

    // Score in the exam
    private int score;

    // Date of the exam
    private LocalDate examDate;
}
