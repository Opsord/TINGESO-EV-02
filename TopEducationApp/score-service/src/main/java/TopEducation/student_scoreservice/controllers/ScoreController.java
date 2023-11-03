package TopEducation.student_scoreservice.controllers;

import TopEducation.student_scoreservice.entities.ScoreEntity;
import TopEducation.student_scoreservice.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scores")

public class ScoreController {

    @Autowired
    ScoreService scoreService;

    // Get all student scores
    @GetMapping
    public ResponseEntity<List<ScoreEntity>> getAllScores() {
        if (scoreService.getAllScores().isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(scoreService.getAllScores());
        }
    }

    // Get all student scores by student RUT
    @GetMapping("/byRUT/{scoreRUT}")
    public ResponseEntity<List<ScoreEntity>> getAllScoresByRUT(@PathVariable("scoreRUT") String scoreRUT) {
        if (scoreService.findAllScoresByStudentRUT(scoreRUT).isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(scoreService.findAllScoresByStudentRUT(scoreRUT));
        }
    }

    // Delete a student score by id
    @GetMapping("/delete/{id}")
    public ResponseEntity<String> deleteScore(@PathVariable("id") Long id) {
        scoreService.deleteGrade(id);
        return ResponseEntity.ok("Score deleted successfully");
    }

    // Delete all student scores
    @GetMapping("/deleteAll")
    public ResponseEntity<String> deleteAllScores() {
        scoreService.deleteAllGrades();
        return ResponseEntity.ok("All scores deleted successfully");
    }

    // Save a student score
    @PostMapping
    public ResponseEntity<ScoreEntity> saveScore(@RequestBody ScoreEntity score) {
        scoreService.saveScore(score);
        return ResponseEntity.ok(score);
    }
}
