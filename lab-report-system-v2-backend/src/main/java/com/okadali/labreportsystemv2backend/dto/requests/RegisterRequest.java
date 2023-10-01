package com.okadali.labreportsystemv2backend.dto.requests;

import com.okadali.labreportsystemv2backend.enums.Role;
import com.okadali.labreportsystemv2backend.exceptions.InvalidBodyException;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterRequest {
    private String name;
    private String surname;
    private String hospitalId;
    private String password;
    private Role role;

    public RegisterRequest(String name, String surname, String hospitalId, String password, Role role) {
        if(name == null || surname == null || hospitalId == null || password == null || role == null) {
            throw new InvalidBodyException("You did not fill the whole form");
        }
        if(hospitalId.length() != 7) {
            throw new InvalidBodyException("Hospital Id must be 7 characters long");
        }

        this.name = name;
        this.surname = surname;
        this.hospitalId = hospitalId;
        this.password = password;
        this.role = role;
    }
}
