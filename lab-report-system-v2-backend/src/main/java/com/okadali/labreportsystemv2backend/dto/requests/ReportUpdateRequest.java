package com.okadali.labreportsystemv2backend.dto.requests;

import lombok.Data;

import java.util.Date;

@Data
public class ReportUpdateRequest {
    String name;
    String surname;
    String tc_id;
    String title;
    String details;
    Date creationDate;
}
