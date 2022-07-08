package com.wator.organizer.repositories;

import com.wator.organizer.entities.CalculatorEntity;
import com.wator.organizer.entities.NotesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepository extends JpaRepository<NotesEntity,Long> {


    List<NotesEntity> findAllByUserid(Integer id);
}
