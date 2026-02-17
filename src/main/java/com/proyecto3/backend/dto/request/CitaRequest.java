package com.proyecto3.backend.dto.request;

import lombok.Data;

@Data
public class CitaRequest {
    private Integer codigo;
    private String descripcion;
    private Integer pacienteId;
    private Integer especialidadId;
}