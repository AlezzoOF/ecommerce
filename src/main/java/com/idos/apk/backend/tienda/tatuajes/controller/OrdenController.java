package com.idos.apk.backend.tienda.tatuajes.controller;

import com.idos.apk.backend.tienda.tatuajes.model.dto.orden.OrdenDtoIn;
import com.idos.apk.backend.tienda.tatuajes.model.dto.orden.OrdenDtoOut;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.OrdenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orden")
public class OrdenController {

    private final OrdenService service;


    public OrdenController(OrdenService service) {
        this.service = service;
    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OrdenDtoOut> crear(@RequestBody OrdenDtoIn orden){
        return new ResponseEntity<>(service.save(orden), HttpStatus.CREATED);
    }

    @GetMapping("/mostrar")
    public ResponseEntity<List<OrdenDtoOut>> mostrar(Long id){
        return new ResponseEntity<>(service.getAllByUser(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(Long id){
        service.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);

    }
}
