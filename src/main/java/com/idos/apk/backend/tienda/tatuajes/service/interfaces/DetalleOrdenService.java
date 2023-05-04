package com.idos.apk.backend.tienda.tatuajes.service.interfaces;

import com.idos.apk.backend.tienda.tatuajes.model.Orden;
import com.idos.apk.backend.tienda.tatuajes.model.Producto;
import com.idos.apk.backend.tienda.tatuajes.model.dto.detalleorden.DetalleOrdenDto;

import java.util.List;

public interface DetalleOrdenService {
    DetalleOrdenDto save(DetalleOrdenDto objeto, Orden orden, Producto producto);

    List<DetalleOrdenDto> getAllByOrden(Long id);

}
