package com.idos.apk.backend.tienda.tatuajes.service.interfaces;

import com.idos.apk.backend.tienda.tatuajes.dto.orden.OrdenAgnoDto;
import com.idos.apk.backend.tienda.tatuajes.dto.orden.OrdenInDto;
import com.idos.apk.backend.tienda.tatuajes.dto.orden.OrdenOutDto;

import java.util.List;

public interface OrdenService {
    OrdenOutDto save(OrdenInDto objeto);

    List<OrdenOutDto> getAllByUser(String token);

    List<OrdenOutDto> getAll();

    List<OrdenOutDto> getAllByDate(int mes, String agno);

    List<OrdenOutDto> getAllByUserAdmin(String id);


    void delete(String id);

    OrdenAgnoDto filtroMes(String agno);
}
