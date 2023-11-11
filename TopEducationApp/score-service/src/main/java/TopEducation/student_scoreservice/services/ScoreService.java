package TopEducation.student_scoreservice.services;

import TopEducation.student_scoreservice.entities.ScoreEntity;
import TopEducation.student_scoreservice.repositories.ScoreRepository;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    // Save a student grade
    public void saveScore(ScoreEntity studentGrade) {
        scoreRepository.save(studentGrade);
    }

    // Delete a student score by rut
    public void deleteScore(String rut) {
        scoreRepository.deleteByRut(rut);
    }

    // Delete all student scores
    @Generated
    public void deleteAllGrades() {
        scoreRepository.deleteAll();
    }

    // Delete all student scores by student RUT
    public void deleteAllScoresByStudentRUT(String scoreRUT) {
        // Get all the scores by student RUT
        List<ScoreEntity> scores = findAllScoresByStudentRUT(scoreRUT);
        // Delete all the scores
        for (ScoreEntity score : scores) {
            deleteScore(score.getScoreRUT());
        }
    }

    // Find by methods

    // Find a student score by id
    public ScoreEntity findScoreById(Long id) {
        return scoreRepository.findById(id).orElse(null);
    }

    // Find all student scores
    public List<ScoreEntity> getAllScores() {
        return (List<ScoreEntity>) scoreRepository.findAll();
    }

    // Find all student scores by student RUT
    public List<ScoreEntity> findAllScoresByStudentRUT(String gradeRUT) {
        return (scoreRepository.findAllScoresByStudentRUT(gradeRUT));
    }
}
