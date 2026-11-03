package com.medicare.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.medicare.entity.Drug;
import com.medicare.exceptionhandling.NotFoundException;
import com.medicare.repository.AdminRepository;
import com.medicare.repository.DrugRepository;
import com.medicare.utils.SuccessResponse;

@Service
public class DrugService {

    private final DrugRepository drugRepo;
    private final AdminRepository adminRepo;

    public DrugService(DrugRepository drugRepo, AdminRepository adminRepo) {
        this.drugRepo = drugRepo;
        this.adminRepo = adminRepo;
    }

    public ResponseEntity<SuccessResponse> saveDrug(Drug drug, int adminId) {
        requireAdmin(adminId);
        Drug saved = drugRepo.save(drug);
        return response(HttpStatus.CREATED, "Drug added successfully", saved);
    }

    public ResponseEntity<SuccessResponse> editDrug(Drug drug, int adminId) {
        requireAdmin(adminId);
        Drug existing = drugRepo.findById(drug.getId())
                .orElseThrow(() -> new NotFoundException("Drug not found with ID: " + drug.getId()));
        if (drug.getName() != null) existing.setName(drug.getName());
        if (drug.getCompany() != null) existing.setCompany(drug.getCompany());
        if (drug.getType() != null) existing.setType(drug.getType());
        if (drug.getPrice() != 0) existing.setPrice(drug.getPrice());
        if (drug.getQuantity() != 0) existing.setQuantity(drug.getQuantity());
        existing.setBanned(drug.isBanned());
        return response(HttpStatus.OK, "Drug updated successfully", drugRepo.save(existing));
    }

    public ResponseEntity<SuccessResponse> updateDrug(Drug drug, int adminId) {
        return editDrug(drug, adminId);
    }

    public ResponseEntity<SuccessResponse> deleteDrug(int id, int adminId) {
        requireAdmin(adminId);
        Drug existing = drugRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Drug not found with ID: " + id));
        drugRepo.delete(existing);
        return response(HttpStatus.OK, "Drug deleted successfully", existing);
    }

    public ResponseEntity<SuccessResponse> findDrugId(int id) {
        Drug drug = drugRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Drug not found with ID: " + id));
        return response(HttpStatus.OK, "Drug details fetched successfully", drug);
    }

    public ResponseEntity<SuccessResponse> findDrugById(int id) {
        return findDrugId(id);
    }

    public ResponseEntity<SuccessResponse> fetchAllDrugs() {
        List<Drug> drugs = drugRepo.findAll();
        return response(HttpStatus.OK, "Drugs fetched successfully", drugs);
    }

    public ResponseEntity<SuccessResponse> searchDrugs(String keyword) {
        return response(HttpStatus.OK, "Drug search completed", drugRepo.findByNameContainingIgnoreCase(keyword));
    }

    private void requireAdmin(int adminId) {
        if (!adminRepo.existsById(adminId)) {
            throw new NotFoundException("Admin not found with ID: " + adminId);
        }
    }

    private ResponseEntity<SuccessResponse> response(HttpStatus status, String message, Object data) {
        SuccessResponse body = SuccessResponse.builder().statusCode(status.value())
                .dateTime(LocalDateTime.now()).message(message).data(data).build();
        return new ResponseEntity<>(body, status);
    }
}
