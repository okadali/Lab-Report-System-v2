package com.okadali.labreportsystemv2backend.dto.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.okadali.labreportsystemv2backend.models.Report;
import com.okadali.labreportsystemv2backend.models.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Data
public class ReportResponse {
    int id;
    String name;
    String surname;
    String tc_id;
    String title;
    String details;
    Date creationDate;
    String doctorName;
    String doctorSurname;
    String imageDataCode;

    public ReportResponse(Report report) {
        this.id = report.getId();
        this.name = report.getName();
        this.surname = report.getSurname();
        this.tc_id = report.getTc_id();
        this.title = report.getTitle();
        this.details = report.getDetails();
        this.creationDate = report.getCreationDate();
        this.doctorName = report.getUser().getName();
        this.doctorSurname = report.getUser().getSurname();
        this.imageDataCode = report.getImageData().getCode();
    }
}
