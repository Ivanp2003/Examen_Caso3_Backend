package com.proyecto3.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EspecialidadResponse {
    private Integer id;
    private String codigo;
    private String nombre;
    private String descripcion;
}