package com.okadali.labreportsystemv2backend.dto.requests;

import com.okadali.labreportsystemv2backend.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String name;
    private String surname;
    private String hospitalId;
    private String password;
    private Role role;
}
