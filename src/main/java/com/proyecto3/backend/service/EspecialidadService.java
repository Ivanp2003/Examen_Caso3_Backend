package com.proyecto3.backend.service;

import com.proyecto3.backend.dto.request.EspecialidadRequest;
import com.proyecto3.backend.dto.response.EspecialidadResponse;
import com.proyecto3.backend.dto.response.MensajeResponse;

import java.util.List;

public interface EspecialidadService {
    List<EspecialidadResponse> listarTodos();
    EspecialidadResponse buscarPorId(Integer id);
    MensajeResponse guardar(EspecialidadRequest request);
    MensajeResponse actualizar(Integer id, EspecialidadRequest request);
    MensajeResponse eliminar(Integer id);
}