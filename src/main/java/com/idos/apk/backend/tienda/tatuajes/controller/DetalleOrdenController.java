package com.idos.apk.backend.tienda.tatuajes.controller;

import com.idos.apk.backend.tienda.tatuajes.dto.detalle.DetalleOrdenOutDto;
import com.idos.apk.backend.tienda.tatuajes.exceptions.OrdenNotFoundException;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.DetalleOrdenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalle")
@RequiredArgsConstructor
public class DetalleOrdenController {
    private final DetalleOrdenService service;

    @GetMapping("/findOne/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DetalleOrdenOutDto showOne(@PathVariable("id") String id) throws OrdenNotFoundException {
        return service.findOne(id);

    }

    @GetMapping("/findAll/{num}")
    @ResponseStatus(HttpStatus.OK)
    public List<DetalleOrdenOutDto> showAll(@PathVariable("num") String num) throws OrdenNotFoundException {
        return service.getAllByOrden(num);
    }


}
