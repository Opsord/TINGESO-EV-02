package TopEducation.studentservice.repositories;

import TopEducation.studentservice.entities.StudentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends CrudRepository<StudentEntity, Long> {
    // Custom query's here

    // Custom query to find a student by RUT
    @Query("SELECT e FROM StudentEntity e WHERE e.rut = :rut")
    StudentEntity findByRut(@Param("rut") String rut);

    // Custom query to delete a student by RUT
    @Query("DELETE FROM StudentEntity e WHERE e.rut = :rut")
    void deleteByRut(@Param("rut") String rut);
}