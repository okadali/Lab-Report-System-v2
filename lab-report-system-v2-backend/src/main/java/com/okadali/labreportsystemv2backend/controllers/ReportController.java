package com.okadali.labreportsystemv2backend.controllers;

import com.okadali.labreportsystemv2backend.dto.other.ResponseData;
import com.okadali.labreportsystemv2backend.dto.requests.ReportCreateRequest;
import com.okadali.labreportsystemv2backend.dto.responses.ReportResponse;
import com.okadali.labreportsystemv2backend.models.Report;
import com.okadali.labreportsystemv2backend.services.ReportService;
import com.okadali.labreportsystemv2backend.utils.TokenHelpers;
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
    public ResponseEntity<ResponseData> createOneReport(@RequestBody ReportCreateRequest request, @RequestHeader("Authorization") String token) {
        request.setToken(TokenHelpers.getToken(token));
        return reportService.createOneReport(request);
    }

    @DeleteMapping("/{reportId}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<ResponseData> deleteOneReport(@PathVariable int reportId) {
        return reportService.deleteOneReport(reportId);
    }






}
