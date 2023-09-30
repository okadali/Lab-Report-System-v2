package com.okadali.labreportsystemv2backend.services;

import com.okadali.labreportsystemv2backend.dto.requests.ReportCreateRequest;
import com.okadali.labreportsystemv2backend.dto.responses.ReportResponse;
import com.okadali.labreportsystemv2backend.exceptions.UserWrongCredsException;
import com.okadali.labreportsystemv2backend.models.Report;
import com.okadali.labreportsystemv2backend.models.User;
import com.okadali.labreportsystemv2backend.repositories.ReportRepository;
import com.okadali.labreportsystemv2backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private  ReportRepository repository;
    private UserRepository userRepository;

    public ReportService(ReportRepository repository,UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public List<ReportResponse> getAllReports() {
        List<Report> list = repository.findAll();
        return list.stream().map(report -> new ReportResponse(report)).collect(Collectors.toList());
    }

    public ReportResponse createOneReport(ReportCreateRequest request) {
        Optional<User> user = userRepository.findById(request.getUser_id());
        if(!user.isPresent()) {
            throw new UserWrongCredsException("User with the id:"+request.getUser_id()+" not found");
        }

        Report report = Report.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .tc_id(request.getTc_id())
                .title(request.getTitle())
                .details(request.getDetails())
                .creationDate(request.getCreationDate())
                .user(user.get())
                .build();

        return new ReportResponse(repository.save(report));
    }
}
