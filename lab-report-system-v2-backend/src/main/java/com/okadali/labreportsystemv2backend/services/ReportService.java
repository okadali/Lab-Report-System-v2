package com.okadali.labreportsystemv2backend.services;

import com.okadali.labreportsystemv2backend.dto.other.ResponseData;
import com.okadali.labreportsystemv2backend.dto.requests.ReportCreateRequest;
import com.okadali.labreportsystemv2backend.dto.responses.ReportResponse;
import com.okadali.labreportsystemv2backend.exceptions.ReportNotFoundException;
import com.okadali.labreportsystemv2backend.exceptions.UserWrongCredsException;
import com.okadali.labreportsystemv2backend.models.Report;
import com.okadali.labreportsystemv2backend.models.User;
import com.okadali.labreportsystemv2backend.repositories.ReportRepository;
import com.okadali.labreportsystemv2backend.repositories.UserRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private  ReportRepository repository;
    private UserRepository userRepository;
    private JwtService jwtService;

    public ReportService(ReportRepository repository,UserRepository userRepository,JwtService jwtService) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public List<ReportResponse> getAllReports() {
        List<Report> list = repository.findAll();
        return list.stream().map(report -> new ReportResponse(report)).collect(Collectors.toList());
    }

    public ResponseEntity<ResponseData> createOneReport(ReportCreateRequest request) {
        String username = jwtService.extractUsername(request.getToken());
        Optional<User> user = userRepository.findByHospitalId(username);

        Report report = Report.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .tc_id(request.getTc_id())
                .title(request.getTitle())
                .details(request.getDetails())
                .creationDate(new Date())
                .user(user.get())
                .build();

        ReportResponse reportResponse = new ReportResponse(repository.save(report));
        ResponseData responseData = new ResponseData(true,"Report Created Successfully",reportResponse);
        return new ResponseEntity<>(responseData,HttpStatus.CREATED);
    }

    public ResponseEntity<ResponseData> deleteOneReport(int reportId) {
        Optional<Report> report = repository.findById(reportId);
        if(!report.isPresent()) {
            throw new ReportNotFoundException("Report "+reportId+" doesn't exist");
        }
        repository.deleteById(reportId);
        ResponseData responseData = new ResponseData(true,"Report Id: "+reportId+" deleted successfully",null);
        return new ResponseEntity<>(responseData,HttpStatus.OK);
    }
}
