package com.idos.apk.backend.tienda.tatuajes.model.dto.producto;

public record ProductoDTOOut(String id,
                             String nombre,
                             String descripcion,
                             double precio,

                             String tipo,
                             int cantidad,
                             String img
) {
}
