package com.okadali.labreportsystemv2backend.repositories;

import com.okadali.labreportsystemv2backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByHospital_id(String hospital_id);
}
