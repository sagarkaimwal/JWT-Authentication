package com.Security.EmployeeManagementSystem.Controller;

import com.Security.EmployeeManagementSystem.DTO.AuthResponse;
import com.Security.EmployeeManagementSystem.DTO.LoginRequest;
import com.Security.EmployeeManagementSystem.DTO.RegisterRequest;
import com.Security.EmployeeManagementSystem.Entity.User;
import com.Security.EmployeeManagementSystem.Service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService service;
     public AuthController(UserService service){
         this.service=service;
     }
     @PostMapping("/register")
     public User register(@RequestBody RegisterRequest request)
     {
         return service.register(request);
     }
     @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request){
         return service.login(request);
     }
}
