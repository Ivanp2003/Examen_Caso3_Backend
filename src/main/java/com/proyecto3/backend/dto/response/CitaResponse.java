package com.proyecto3.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CitaResponse {
    private Integer id;
    private Integer codigo;
    private String descripcion;
    private Integer pacienteId;
    private String pacienteNombre;
    private String pacienteApellido;
    private Integer especialidadId;
    private String especialidadNombre;
}