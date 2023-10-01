package com.okadali.labreportsystemv2backend.dto.requests;

import com.okadali.labreportsystemv2backend.exceptions.InvalidBodyException;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationRequest {
    private String hospitalId;
    String password;

    AuthenticationRequest(String hospitalId, String password) {
        if(hospitalId == null || password == null) {
            throw new InvalidBodyException("You must provide a valid hospitalId and password");
        }

        this.hospitalId = hospitalId;
        this.password = password;
    }
}
