package com.idos.apk.backend.tienda.tatuajes.service.interfaces;

import com.idos.apk.backend.tienda.tatuajes.model.Orden;
import com.idos.apk.backend.tienda.tatuajes.model.Producto;
import com.idos.apk.backend.tienda.tatuajes.model.dto.detalleorden.DetalleOrdenDto;
import com.idos.apk.backend.tienda.tatuajes.model.dto.producto.ProductoDTOIn;
import com.idos.apk.backend.tienda.tatuajes.model.dto.producto.ProductoDTOOut;
import com.idos.apk.backend.tienda.tatuajes.model.dto.producto.ProductoPageableResponse;

import java.util.List;

public interface DetalleOrdenService {
    DetalleOrdenDto save(DetalleOrdenDto objeto, Orden orden , Producto producto);
    List<DetalleOrdenDto> getAllByOrden(Long id);

}
