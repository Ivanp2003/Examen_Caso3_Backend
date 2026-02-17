package com.proyecto3.backend.dto.request;

import lombok.Data;

@Data
public class PacienteRequest {
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