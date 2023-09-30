package com.okadali.labreportsystemv2backend.services;

import com.okadali.labreportsystemv2backend.dto.other.ResponseData;
import com.okadali.labreportsystemv2backend.dto.requests.AuthenticationRequest;
import com.okadali.labreportsystemv2backend.dto.requests.RegisterRequest;
import com.okadali.labreportsystemv2backend.dto.responses.AuthenticationResponse;
import com.okadali.labreportsystemv2backend.exceptions.HospitalIdAlreadyExistsException;
import com.okadali.labreportsystemv2backend.exceptions.UserWrongCredsException;
import com.okadali.labreportsystemv2backend.models.User;
import com.okadali.labreportsystemv2backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<ResponseData> register(RegisterRequest request) {
        if(userRepository.findByHospitalId(request.getHospitalId()).isPresent()) {
            throw new HospitalIdAlreadyExistsException("Hospital Id "+request.getHospitalId()+" already exists");
        }

        var user = User.builder()
                .hospitalId(request.getHospitalId())
                .name(request.getName())
                .surname(request.getSurname())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder().token(jwtToken).build();

        ResponseData responseData = new ResponseData(true,"You Registered Successfully",authenticationResponse);
        return new ResponseEntity<>(responseData,HttpStatus.CREATED);
    }

    public ResponseEntity<ResponseData> authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getHospitalId(),
                            request.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            throw new UserWrongCredsException("The Credentials You've Entered Didn't Match Any User");
        }

        var user = userRepository.findByHospitalId(request.getHospitalId())
                .orElseThrow(() -> new UserWrongCredsException("This user is not exists"));

        var jwtToken = jwtService.generateToken(user);

        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder().token(jwtToken).build();

        ResponseData responseData = new ResponseData(true,"You Authenticated Successfully",authenticationResponse);
        return new ResponseEntity(responseData, HttpStatus.OK);
    }
}
