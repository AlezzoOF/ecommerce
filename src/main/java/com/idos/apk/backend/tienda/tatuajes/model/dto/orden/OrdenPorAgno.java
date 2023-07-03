package com.idos.apk.backend.tienda.tatuajes.model.dto.orden;

import lombok.Builder;

import java.util.List;

@Builder
public record OrdenPorAgno(List<OrdenPorMes>lista, double totalDinero, int totalOrd) {
}
