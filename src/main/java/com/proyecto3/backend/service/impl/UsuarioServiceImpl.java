package com.proyecto3.backend.service.impl;

import com.proyecto3.backend.dto.request.LoginRequest;
import com.proyecto3.backend.dto.response.LoginResponse;
import com.proyecto3.backend.model.entity.Usuario;
import com.proyecto3.backend.repository.UsuarioRepository;
import com.proyecto3.backend.security.jwt.JwtUtil;
import com.proyecto3.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public LoginResponse login(LoginRequest request) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(request.getEmail());

        if (usuarioOpt.isEmpty()) {
            return new LoginResponse(null, null, null, null,
                    "Usuario o contraseña incorrectos.");
        }

        Usuario usuario = usuarioOpt.get();

        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            return new LoginResponse(null, null, null, null,
                    "Usuario o contraseña incorrectos.");
        }

        String token = jwtUtil.generateToken(usuario.getEmail());

        return new LoginResponse(
                token,
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getEmail(),
                "Login exitoso"
        );
    }
}