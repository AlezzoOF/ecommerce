package com.idos.apk.backend.tienda.tatuajes.service.interfaces;

import com.idos.apk.backend.tienda.tatuajes.dto.detalle.DetalleOrdenInDto;
import com.idos.apk.backend.tienda.tatuajes.dto.detalle.DetalleOrdenOutDto;


import java.util.List;

public interface DetalleOrdenService {
    DetalleOrdenInDto save(DetalleOrdenInDto objeto, String numeroOrden);

    DetalleOrdenOutDto findOne(String id);

    List<DetalleOrdenOutDto> getAllByOrden(String num);

}
