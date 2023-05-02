package com.idos.apk.backend.tienda.tatuajes.model.dto;

public record ProductoDTOIn(String nombre,
                            String descripcion,
                            String img,
                            double precio,
                            int cantidad,
                            String tipo) {
}
