package com.idos.apk.backend.tienda.tatuajes.model.dto.orden;

import com.idos.apk.backend.tienda.tatuajes.model.dto.detalleorden.DetalleOrdenDto;

import java.util.List;

public record OrdenDtoIn(List<DetalleOrdenDto> lista, String token, double total) {
}
