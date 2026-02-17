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
        System.out.println("=== LOGIN DEBUG ===");
        System.out.println("Email recibido: " + request.getEmail());
        System.out.println("Password recibido: " + request.getPassword());

        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(request.getEmail());

        if (usuarioOpt.isEmpty()) {
            System.out.println("Usuario NO encontrado en BD");
            return new LoginResponse(null, null, null, null,
                    "Usuario o contraseña incorrectos.");
        }

        Usuario usuario = usuarioOpt.get();
        System.out.println("Usuario encontrado: " + usuario.getEmail());
        System.out.println("Password en BD: " + usuario.getPassword());
        System.out.println("Resultado de matches: " + passwordEncoder.matches(request.getPassword(), usuario.getPassword()));

        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            System.out.println("Password NO coincide");
            return new LoginResponse(null, null, null, null,
                    "Usuario o contraseña incorrectos.");
        }

        System.out.println("Login EXITOSO");
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