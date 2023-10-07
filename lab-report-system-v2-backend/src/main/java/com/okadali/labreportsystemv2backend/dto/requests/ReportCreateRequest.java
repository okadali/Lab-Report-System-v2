package com.okadali.labreportsystemv2backend.dto.requests;

import com.okadali.labreportsystemv2backend.exceptions.InvalidBodyException;
import lombok.*;

@Data
public class ReportCreateRequest {

    private final String name;
    private final String surname;
    private final String tc_id;
    private final String title;
    private final String details;
    private final String imageDataCode;
    private String token;

    public ReportCreateRequest(String name, String surname, String tc_id, String title, String details,String imageDataCode) {
        if(name == null || surname == null || tc_id == null || title == null || details ==  null || imageDataCode == null) {
            throw new InvalidBodyException("You provided not enough data for creating a report");
        }

        this.name = name;
        this.surname = surname;
        this.tc_id = tc_id;
        this.title = title;
        this.details = details;
        this.imageDataCode = imageDataCode;
    }
}
