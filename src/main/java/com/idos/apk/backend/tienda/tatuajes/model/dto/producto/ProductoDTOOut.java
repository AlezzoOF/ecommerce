package com.idos.apk.backend.tienda.tatuajes.model.dto.producto;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public record ProductoDTOOut(String id,
                             String nombre,
                             String descripcion,
                             double precio,

                             int cantidad,
                             String img
                             ) {
}
