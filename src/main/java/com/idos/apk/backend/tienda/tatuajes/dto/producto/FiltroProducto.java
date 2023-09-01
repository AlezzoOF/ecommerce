package com.idos.apk.backend.tienda.tatuajes.dto.producto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FiltroProducto {
    private String tipo;
    private boolean enable;
    private Double precioMinimo;
    private Double precioMaximo;
}
