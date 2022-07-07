package com.wator.organizer.repositories;

import com.wator.organizer.entities.CalculatorEntity;
import com.wator.organizer.entities.LogsEntity;
import com.wator.organizer.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogsRepository extends JpaRepository<LogsEntity, Long> {
    List<LogsEntity> findAll();
    List<LogsEntity> findAllByUserid(Integer id);
}
