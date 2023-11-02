package TopEducation.installmentservice.repositories;

import TopEducation.installmentservice.entities.InstallmentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface InstallmentRepository extends CrudRepository<InstallmentEntity, Long> {

    // Custom query's here

    // Custom query to find all installments for a given student
    @Query("SELECT e FROM InstallmentEntity e WHERE e.installmentRUT = :installmentRUT")
    List<InstallmentEntity> findAllInstallmentsByRUT(@Param("installmentRUT") String installmentRUT);

    // Custom query to find all paid installments for a given student
    @Query("SELECT e FROM InstallmentEntity e WHERE e.installmentRUT = :installmentRUT AND e.installmentStatus = 1")
    List<InstallmentEntity> findAllPaidInstallmentsByRUT(@Param("installmentRUT") String installmentRUT);

    // Custom query to find all overdue installments for a given student
    @Query("SELECT e FROM InstallmentEntity e WHERE e.installmentRUT = :installmentRUT AND e.installmentOverdueStatus = 1")
    List<InstallmentEntity> findAllOverdueInstallmentsByRUT(@Param("installmentRUT") String installmentRUT);
}
