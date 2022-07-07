package com.wator.organizer.repositories;

import com.wator.organizer.entities.CalculatorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CalculatorRepository extends JpaRepository<CalculatorEntity, Long> {
    List<CalculatorEntity> findAll();
    List<CalculatorEntity> findAllByUserId(Integer id);
}
