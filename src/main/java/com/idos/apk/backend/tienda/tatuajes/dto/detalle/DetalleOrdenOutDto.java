package com.idos.apk.backend.tienda.tatuajes.dto.detalle;

import com.idos.apk.backend.tienda.tatuajes.dto.producto.ProductoOutDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleOrdenOutDto {
    private Integer cantidad;
    private ProductoOutDto producto;
    private Double total;
}
