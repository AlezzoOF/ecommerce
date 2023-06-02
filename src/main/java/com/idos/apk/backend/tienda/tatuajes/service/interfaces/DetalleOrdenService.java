package com.idos.apk.backend.tienda.tatuajes.service.interfaces;

import com.idos.apk.backend.tienda.tatuajes.model.dto.detalleorden.DetalleOrdenDto;
import com.idos.apk.backend.tienda.tatuajes.model.dto.detalleorden.DetalleOrdenDtoOne;

import java.util.List;

public interface DetalleOrdenService {
    DetalleOrdenDto save(DetalleOrdenDto objeto, String numeroOrden);

    DetalleOrdenDtoOne findOne(String id);

    List<DetalleOrdenDto> getAllByOrden(String num);

}
