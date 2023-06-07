package com.idos.apk.backend.tienda.tatuajes.controller;

import com.idos.apk.backend.tienda.tatuajes.exceptions.OrdenNotFoundException;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.DetalleOrdenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/detalle")
@CrossOrigin(origins = "*")
public class DetalleOrdenController {
    private final DetalleOrdenService service;

    public DetalleOrdenController(DetalleOrdenService service) {
        this.service = service;
    }

    @GetMapping("/findOne/{id}")
    public ResponseEntity showOne(@PathVariable("id") String id) throws OrdenNotFoundException {
        return new ResponseEntity<>(service.findOne(id), HttpStatus.OK);

    }

    @GetMapping("/findAll/{num}")
    public ResponseEntity showAll(@PathVariable("num") String num) throws OrdenNotFoundException {
        return new ResponseEntity<>(service.getAllByOrden(num), HttpStatus.OK);
    }


}
