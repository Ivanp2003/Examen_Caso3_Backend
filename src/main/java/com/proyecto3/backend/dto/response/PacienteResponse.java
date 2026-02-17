package com.proyecto3.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteResponse {
    private Integer id;
    private String nombre;
    private String apellido;
    private String cedula;
    private String fechaNacimiento;
    private String genero;
    private String ciudad;
    private String direccion;
    private String telefono;
    private String email;
}