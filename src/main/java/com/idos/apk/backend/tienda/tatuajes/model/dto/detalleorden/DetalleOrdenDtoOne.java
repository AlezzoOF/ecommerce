package com.idos.apk.backend.tienda.tatuajes.model.dto.detalleorden;

import com.idos.apk.backend.tienda.tatuajes.model.dto.producto.ProductoDTOOut;

public record DetalleOrdenDtoOne(int cantidad,
                                 ProductoDTOOut producto,
                                 double total) {
}
