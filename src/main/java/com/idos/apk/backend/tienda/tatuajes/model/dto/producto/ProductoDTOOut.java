package com.idos.apk.backend.tienda.tatuajes.model.dto.producto;

import org.springframework.web.multipart.MultipartFile;

public record ProductoDTOOut(String nombre,
                             String descripcion,
                             String img,
                             double precio,
                             boolean enable,
                             int cantidad,
                             Long id) {
}
