package com.okadali.labreportsystemv2backend.services;

import com.okadali.labreportsystemv2backend.dto.requests.AuthenticationRequest;
import com.okadali.labreportsystemv2backend.dto.requests.RegisterRequest;
import com.okadali.labreportsystemv2backend.dto.responses.AuthenticationResponse;
import com.okadali.labreportsystemv2backend.exceptions.UserNotFoundException;
import com.okadali.labreportsystemv2backend.models.User;
import com.okadali.labreportsystemv2backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .hospital_id(request.getHospital_id())
                .name(request.getName())
                .surname(request.getSurname())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getHospital_id(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByHospital_id(request.getHospital_id())
                .orElseThrow(() -> new UserNotFoundException("There is no user with this hospital id"));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
