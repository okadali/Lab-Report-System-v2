package com.okadali.labreportsystemv2backend.repositories;

import com.okadali.labreportsystemv2backend.models.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository  extends JpaRepository<Report,Integer> {
}
