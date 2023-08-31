package com.idos.apk.backend.tienda.tatuajes.service.interfaces;

import com.idos.apk.backend.tienda.tatuajes.dto.detalle.DetalleOrdenDto;
import com.idos.apk.backend.tienda.tatuajes.dto.detalle.OneDetalleOrdenDto;


import java.util.List;

public interface DetalleOrdenService {
    DetalleOrdenDto save(DetalleOrdenDto objeto, String numeroOrden);

    OneDetalleOrdenDto findOne(String id);

    List<OneDetalleOrdenDto> getAllByOrden(String num);

}
