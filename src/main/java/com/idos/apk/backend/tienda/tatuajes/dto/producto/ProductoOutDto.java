package com.idos.apk.backend.tienda.tatuajes.dto.producto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductoOutDto {
    private String id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String tipo;
    private Integer cantidad;
    private String img;
}
