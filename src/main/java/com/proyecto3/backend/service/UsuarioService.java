package com.proyecto3.backend.service;

import com.proyecto3.backend.dto.request.LoginRequest;
import com.proyecto3.backend.dto.response.LoginResponse;

public interface UsuarioService {
    LoginResponse login(LoginRequest request);
}