package TopEducation.installmentservice.controllers;

import TopEducation.installmentservice.entities.InstallmentEntity;
import TopEducation.installmentservice.services.InstallmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/installments")

public class InstallmentController {

    @Autowired
    InstallmentService installmentService;

    //Get all installments
    @GetMapping
    public ResponseEntity<List<InstallmentEntity>> getAllInstallments() {
        if (installmentService.getAllInstallments().isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(installmentService.getAllInstallments());
        }
    }

    //Get all installments by student RUT
    @GetMapping("/byRUT/{installmentRUT}")
    public ResponseEntity<List<InstallmentEntity>> getAllInstallmentsByRUT(@PathVariable("installmentRUT") String installmentRUT) {
        if (installmentService.findAllByInstallmentRUT(installmentRUT).isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(installmentService.findAllByInstallmentRUT(installmentRUT));
        }
    }

    //Get all paid installments by student RUT
    @GetMapping("/byRUT/paid/{installmentRUT}")
    public ResponseEntity<List<InstallmentEntity>> getAllPaidInstallmentsByRUT(@PathVariable("installmentRUT") String installmentRUT) {
        if (installmentService.findAllPaidInstallmentsByRUT(installmentRUT).isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(installmentService.findAllPaidInstallmentsByRUT(installmentRUT));
        }
    }

    //Get all overdue installments by student RUT
    @GetMapping("/byRUT/overdue/{installmentRUT}")
    public ResponseEntity<List<InstallmentEntity>> getAllOverdueInstallmentsByRUT(@PathVariable("installmentRUT") String installmentRUT) {
        if (installmentService.findAllOverdueInstallmentsByRUT(installmentRUT).isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(installmentService.findAllOverdueInstallmentsByRUT(installmentRUT));
        }
    }

    //Delete an installment
    @GetMapping("/delete/{id}")
    public ResponseEntity<String> deleteInstallment(@PathVariable("id") Long id) {
        try {
            installmentService.deleteInstallment(id);
            return ResponseEntity.ok("Installment deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error deleting installment");
        }
    }

    //Delete all installments
    @GetMapping("/deleteAll")
    public ResponseEntity<String> deleteAllInstallments() {
        try {
            installmentService.deleteAllInstallments();
            return ResponseEntity.ok("All installments deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error deleting installments");
        }
    }
}