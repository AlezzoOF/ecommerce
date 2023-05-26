package com.idos.apk.backend.tienda.tatuajes.model.dto.producto;

public record ProductoDTOOut(String nombre,
                             String descripcion,
                             String img,
                             double precio,
                             boolean enable,
                             int cantidad,
                             Long id) {
}
