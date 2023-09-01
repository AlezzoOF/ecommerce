package com.idos.apk.backend.tienda.tatuajes.dto.orden;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdenAgnoDto {
    private List<OrdenMesDto> lista;
    private double totalDinero;
    private int totalOrd;
}
