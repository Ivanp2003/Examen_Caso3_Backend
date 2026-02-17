package com.proyecto3.backend.service;

import com.proyecto3.backend.dto.request.PacienteRequest;
import com.proyecto3.backend.dto.response.MensajeResponse;
import com.proyecto3.backend.dto.response.PacienteResponse;

import java.util.List;

public interface PacienteService {
    List<PacienteResponse> listarTodos();
    PacienteResponse buscarPorId(Integer id);
    MensajeResponse guardar(PacienteRequest request);
    MensajeResponse actualizar(Integer id, PacienteRequest request);
    MensajeResponse eliminar(Integer id);
}