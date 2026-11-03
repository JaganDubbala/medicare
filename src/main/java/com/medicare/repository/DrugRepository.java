package com.medicare.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medicare.entity.Drug;

@Repository
public interface DrugRepository extends JpaRepository<Drug, Integer> {
    Optional<Drug> findByNameIgnoreCase(String name);
    List<Drug> findByNameContainingIgnoreCase(String keyword);
}
