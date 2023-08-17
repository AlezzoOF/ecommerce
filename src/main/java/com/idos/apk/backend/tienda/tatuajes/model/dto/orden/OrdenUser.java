package com.idos.apk.backend.tienda.tatuajes.model.dto.orden;

import com.idos.apk.backend.tienda.tatuajes.model.dto.detalleorden.DetalleOrdenDtoOne;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record OrdenUser(double total, String numero, LocalDate fechaCreacion, List<DetalleOrdenDtoOne> detalles) {
}
