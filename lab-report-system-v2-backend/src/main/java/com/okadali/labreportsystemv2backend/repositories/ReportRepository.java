package com.okadali.labreportsystemv2backend.repositories;

import com.okadali.labreportsystemv2backend.models.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReportRepository  extends JpaRepository<Report,Integer>, JpaSpecificationExecutor<Report> {
}
