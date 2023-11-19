package com.okadali.labreportsystemv2backend.services;

import com.okadali.labreportsystemv2backend.dto.other.PaginationWrapper;
import com.okadali.labreportsystemv2backend.dto.other.ResponseData;
import com.okadali.labreportsystemv2backend.dto.requests.ReportCreateRequest;
import com.okadali.labreportsystemv2backend.dto.requests.ReportUpdateRequest;
import com.okadali.labreportsystemv2backend.dto.responses.ReportResponse;
import com.okadali.labreportsystemv2backend.exceptions.FileCodeNotFoundException;
import com.okadali.labreportsystemv2backend.exceptions.ReportNotFoundException;
import com.okadali.labreportsystemv2backend.models.ImageData;
import com.okadali.labreportsystemv2backend.models.Report;
import com.okadali.labreportsystemv2backend.models.User;
import com.okadali.labreportsystemv2backend.repositories.ReportRepository;
import com.okadali.labreportsystemv2backend.repositories.StorageRepository;
import com.okadali.labreportsystemv2backend.repositories.UserRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private  ReportRepository repository;
    private UserRepository userRepository;

    private StorageRepository storageRepository;
    private JwtService jwtService;

    public ReportService(ReportRepository repository,UserRepository userRepository,JwtService jwtService, StorageRepository storageRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.storageRepository = storageRepository;
    }


    public ResponseEntity<ResponseData> createOneReport(ReportCreateRequest request) {
        String username = jwtService.extractUsername(request.getToken());
        Optional<User> user = userRepository.findByHospitalId(username);
        Optional<ImageData> imageData = storageRepository.findByCode(request.getImageDataCode());
        if(!imageData.isPresent()) {
            throw new FileCodeNotFoundException("File code: "+request.getImageDataCode()+" not found");
        }


        Report report = Report.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .tc_id(request.getTc_id())
                .title(request.getTitle())
                .details(request.getDetails())
                .imageData(imageData.get())
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

    public ResponseEntity<ResponseData> updateOneReport(int reportId,ReportUpdateRequest request) {
        Optional<Report> reportOptional = repository.findById(reportId);
        if(!reportOptional.isPresent()) {
            throw new ReportNotFoundException("Report "+reportId+" doesn't exist");
        }

        Report report = reportOptional.get();

        if(request.getTc_id() != null) report.setTc_id(request.getTc_id());
        if(request.getTitle() != null) report.setTitle(request.getTitle());
        if(request.getName() != null) report.setName(request.getName());
        if(request.getSurname() != null) report.setSurname(request.getSurname());
        if(request.getDetails() != null) report.setDetails(request.getDetails());
        if(request.getCreationDate() != null) report.setCreationDate(request.getCreationDate());

        report = repository.save(report);
        ResponseData responseData = new ResponseData(true,"Report Id: "+reportId+" updated successfully",report);
        return new ResponseEntity<>(responseData,HttpStatus.OK);
    }

    public ResponseEntity<ResponseData> getAllReports(
            Optional<String> name,
            Optional<String> surname,
            Optional<String> tc_id,
            Optional<String> userName,
            Optional<String> userSurname,
            Optional<Boolean> sortByDate,
            Pageable pageable) {

        Specification<Report> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            name.ifPresent(n -> predicates.add(criteriaBuilder.equal(root.get("name"), n)));
            surname.ifPresent(s -> predicates.add(criteriaBuilder.equal(root.get("surname"), s)));
            tc_id.ifPresent(id -> predicates.add(criteriaBuilder.equal(root.get("tc_id"), id)));
            userName.ifPresent(uName -> predicates.add(criteriaBuilder.equal(root.get("user").get("name"), uName)));
            userSurname.ifPresent(uSurname -> predicates.add(criteriaBuilder.equal(root.get("user").get("surname"), uSurname)));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        Sort sort = sortByDate.orElse(false) ? Sort.by(Sort.Direction.ASC, "creationDate") : Sort.unsorted();

        Pageable pageableWithSorting = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        Page<Report> reportPage = repository.findAll(specification,pageableWithSorting);

        List<ReportResponse> reportResponses = reportPage.getContent()
                .stream()
                .map(report -> new ReportResponse(report))
                .collect(Collectors.toList());

        PaginationWrapper paginationWrapper = new PaginationWrapper(reportPage.getTotalPages()-1,pageable.getPageNumber(),reportResponses.size(),reportResponses);
        ResponseData responseData = new ResponseData(true,"Reports fetched successfully",paginationWrapper);
        ResponseEntity responseEntity = new ResponseEntity(responseData,HttpStatus.OK);

        return responseEntity;
    }
}
