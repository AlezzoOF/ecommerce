package com.idos.apk.backend.tienda.tatuajes.model.dto.producto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class FiltroProducto {
    private String tipo;
    private boolean enable;
    private double precioMinimo;
    private double precioMaximo;
}
