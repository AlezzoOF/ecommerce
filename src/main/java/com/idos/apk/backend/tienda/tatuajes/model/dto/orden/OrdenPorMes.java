package com.idos.apk.backend.tienda.tatuajes.model.dto.orden;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdenPorMes {
    private int mes;
    private int cantOrdenes;
    private double totalMes;
    private double porciento;
}
