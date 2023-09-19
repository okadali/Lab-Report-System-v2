package com.okadali.labreportsystemv2backend.controllers;

import com.okadali.labreportsystemv2backend.dto.requests.ReportCreateRequest;
import com.okadali.labreportsystemv2backend.dto.responses.ReportResponse;
import com.okadali.labreportsystemv2backend.models.Report;
import com.okadali.labreportsystemv2backend.services.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reports")
public class ReportController {

    private ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public List<ReportResponse> getAllReports() {
        return reportService.getAllReports();
    }

    @PostMapping
    public ReportResponse createOneReport(@RequestBody ReportCreateRequest request) {
        return reportService.createOneReport(request);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<String> deleteOneReport() {
        return ResponseEntity.ok().body("Content Deleted");
    }






}
