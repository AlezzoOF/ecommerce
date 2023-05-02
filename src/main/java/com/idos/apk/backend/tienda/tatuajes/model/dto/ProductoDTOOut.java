package com.idos.apk.backend.tienda.tatuajes.model.dto;

public record ProductoDTOOut(String nombre,
                             String descripcion,
                             String img,
                             double precio,
                             boolean enable,
                             Long id) {
}
