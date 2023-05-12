package com.idos.apk.backend.tienda.tatuajes.model.dto.orden;

import com.idos.apk.backend.tienda.tatuajes.model.dto.detalleorden.DetalleOrdenDto;

import java.util.Date;
import java.util.List;

public record OrdenDtoOut(double total, String numero, Date fechaCreacion) {
}
