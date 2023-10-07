package com.okadali.labreportsystemv2backend.repositories;

import com.okadali.labreportsystemv2backend.models.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StorageRepository extends JpaRepository<ImageData,Long> {
    Optional<ImageData> findByCode(String codeName);
}
