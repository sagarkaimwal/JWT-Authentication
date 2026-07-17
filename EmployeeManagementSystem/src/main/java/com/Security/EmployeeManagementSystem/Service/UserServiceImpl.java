package com.Security.EmployeeManagementSystem.Service;

import com.Security.EmployeeManagementSystem.DTO.AuthResponse;
import com.Security.EmployeeManagementSystem.DTO.LoginRequest;
import com.Security.EmployeeManagementSystem.DTO.RegisterRequest;
import com.Security.EmployeeManagementSystem.Entity.User;
import com.Security.EmployeeManagementSystem.Repository.UserRepository;
import com.Security.EmployeeManagementSystem.Security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserServiceImpl(
            UserRepository repository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtService jwtService){
        this.repository=repository;
        this.passwordEncoder=passwordEncoder;
        this.authenticationManager=authenticationManager;
        this.jwtService=jwtService;
    }
    @Override
    public User register(RegisterRequest request) {
        User user=User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("USER")
                .build();
        return repository.save(user);
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user=repository.findByEmail(request.getEmail()).orElseThrow();
        String token=jwtService.generateToken(user);
        return new AuthResponse(token);
    }
}
