package com.idos.apk.backend.tienda.tatuajes.service.interfaces;

import com.idos.apk.backend.tienda.tatuajes.model.dto.orden.OrdenDtoIn;
import com.idos.apk.backend.tienda.tatuajes.model.dto.orden.OrdenDtoOut;

import java.util.List;

public interface OrdenService {
    OrdenDtoOut save(OrdenDtoIn objeto);
    List<OrdenDtoOut> getAllByUser(Long id);
    void delete(Long id);
}
