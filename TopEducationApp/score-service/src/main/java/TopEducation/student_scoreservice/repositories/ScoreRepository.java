package TopEducation.student_scoreservice.repositories;

import TopEducation.student_scoreservice.entities.ScoreEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ScoreRepository extends CrudRepository<ScoreEntity, Long> {
    // Custom query's here

    // Custom query to find all scores for a given student
    @Query("SELECT e FROM ScoreEntity e WHERE e.scoreRUT = :scoreRUT")
    List<ScoreEntity> findAllScoresByStudentRUT(@Param("scoreRUT") String scoreRUT);

    // Custom query to delete a score by student RUT
    @Query("DELETE FROM ScoreEntity e WHERE e.scoreRUT = :scoreRUT")
    void deleteByRut(@Param("scoreRUT") String scoreRUT);
}
