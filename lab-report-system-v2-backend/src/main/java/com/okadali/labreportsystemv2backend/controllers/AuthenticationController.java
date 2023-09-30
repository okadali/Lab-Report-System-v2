package com.okadali.labreportsystemv2backend.controllers;

import com.okadali.labreportsystemv2backend.dto.other.ResponseData;
import com.okadali.labreportsystemv2backend.dto.requests.AuthenticationRequest;
import com.okadali.labreportsystemv2backend.dto.requests.RegisterRequest;
import com.okadali.labreportsystemv2backend.dto.responses.AuthenticationResponse;
import com.okadali.labreportsystemv2backend.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService service) {
        this.authenticationService = service;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseData> register(
            @RequestBody RegisterRequest request
    ) {
        return authenticationService.register(request);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseData> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return authenticationService.authenticate(request);
    }

}
