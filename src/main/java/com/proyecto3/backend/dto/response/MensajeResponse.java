package com.proyecto3.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensajeResponse {
    private String mensaje;
    private boolean exito;
    private Object data;
}
