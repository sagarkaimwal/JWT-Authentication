package com.Security.EmployeeManagementSystem.Service;

import com.Security.EmployeeManagementSystem.DTO.AuthResponse;
import com.Security.EmployeeManagementSystem.DTO.LoginRequest;
import com.Security.EmployeeManagementSystem.DTO.RegisterRequest;
import com.Security.EmployeeManagementSystem.Entity.User;

public interface UserService {
    User register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
