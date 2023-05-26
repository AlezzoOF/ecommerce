package com.idos.apk.backend.tienda.tatuajes.model.dto.producto;

import org.springframework.web.multipart.MultipartFile;

public record ProductoDTOIn(String nombre,
                            String descripcion,
                            double precio,
                            int cantidad,
                            String tipo) {
}
