package com.proyecto3.backend.service;

import com.proyecto3.backend.dto.request.CitaRequest;
import com.proyecto3.backend.dto.response.CitaResponse;
import com.proyecto3.backend.dto.response.MensajeResponse;

import java.util.List;

public interface CitaService {
    List<CitaResponse> listarTodos();
    CitaResponse buscarPorId(Integer id);
    MensajeResponse guardar(CitaRequest request);
    MensajeResponse actualizar(Integer id, CitaRequest request);
    MensajeResponse eliminar(Integer id);
}