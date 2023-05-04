package com.idos.apk.backend.tienda.tatuajes.controller;

import com.idos.apk.backend.tienda.tatuajes.model.dto.detalleorden.DetalleOrdenDto;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.DetalleOrdenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/detalle")
public class DetalleOrdenController {
    private final DetalleOrdenService service;

    public DetalleOrdenController(DetalleOrdenService service) {
        this.service = service;
    }

    @GetMapping("/ver/{id}")
    public ResponseEntity<List<DetalleOrdenDto>> mostrar(@PathVariable("id") Long id){
        return new ResponseEntity<>(service.getAllByOrden(id), HttpStatus.OK);
    }


}
