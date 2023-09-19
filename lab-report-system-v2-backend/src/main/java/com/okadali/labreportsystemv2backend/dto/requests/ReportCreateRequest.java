package com.okadali.labreportsystemv2backend.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportCreateRequest {

    String name;
    String surname;
    String tc_id;
    String title;
    String details;
    Date creationDate;
    int user_id;
}
