package com.idos.apk.backend.tienda.tatuajes.dto.orden;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdenMesDto {
    private Integer mes;
    private Integer cantOrdenes;
    private Double totalMes;
    private Double porciento;
}
