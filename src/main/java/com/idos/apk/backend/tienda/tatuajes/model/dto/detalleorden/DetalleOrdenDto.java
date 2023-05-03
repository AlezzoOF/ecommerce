package com.idos.apk.backend.tienda.tatuajes.model.dto.detalleorden;

import com.idos.apk.backend.tienda.tatuajes.model.dto.producto.ProductoDTOIn;


public record DetalleOrdenDto(int cantidad, Long producto_id, double total) {
}
