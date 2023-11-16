package com.okadali.labreportsystemv2backend.controllers;

import com.okadali.labreportsystemv2backend.dto.other.ResponseData;
import com.okadali.labreportsystemv2backend.dto.requests.ReportCreateRequest;
import com.okadali.labreportsystemv2backend.dto.requests.ReportUpdateRequest;
import com.okadali.labreportsystemv2backend.services.ReportService;
import com.okadali.labreportsystemv2backend.utils.TokenUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/reports")
public class ReportController {

    private ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public ResponseEntity<ResponseData> getAllReports(
            @RequestParam Optional<String> name,
            @RequestParam Optional<String> surname,
            @RequestParam Optional<String> tc_id,
            @RequestParam Optional<String> userName,
            @RequestParam Optional<String> userSurname,
            @RequestParam Optional<Boolean> sortByDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page,size);
        return reportService.getAllReports(name,surname,tc_id,userName,userSurname,sortByDate,pageable);
    }

    @PutMapping("/{reportId}")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<ResponseData> updateOneReport(@PathVariable int reportId, @RequestBody ReportUpdateRequest request) {
        return reportService.updateOneReport(reportId,request);
    }

    @PostMapping
    public ResponseEntity<ResponseData> createOneReport(@RequestBody ReportCreateRequest request, @RequestHeader("Authorization") String token) {
        request.setToken(TokenUtils.getToken(token));
        return reportService.createOneReport(request);
    }

    @DeleteMapping("/{reportId}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<ResponseData> deleteOneReport(@PathVariable int reportId) {
        return reportService.deleteOneReport(reportId);
    }
}
