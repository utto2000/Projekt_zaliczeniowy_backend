package com.wator.organizer.repositories;

import com.wator.organizer.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findAll();
    Optional<UserEntity> findByEmail (String email);
    Optional<UserEntity> findByEmailAndPassword(String email, String password);
}
