package com.idos.apk.backend.tienda.tatuajes.controller;

import com.idos.apk.backend.tienda.tatuajes.model.dto.detalleorden.DetalleOrdenDto;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.DetalleOrdenService;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalle")
@CrossOrigin(origins = "*")
public class DetalleOrdenController {
    private final DetalleOrdenService service;

    public DetalleOrdenController(DetalleOrdenService service) {
        this.service = service;
    }
    @GetMapping("/findOne/{id}")
    public ResponseEntity showOne(@PathVariable("id") String id){
        try {
            return new ResponseEntity<>(service.findOne(id), HttpStatus.OK);
        }catch (DataAccessException ex){
            return new ResponseEntity<>("Error al conectar con la base de datos", HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (RuntimeException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAll/{num}")
    public ResponseEntity showAll(@PathVariable("num")String num){
        try {
            return new ResponseEntity<>(service.getAllByOrden(num), HttpStatus.OK);
        }catch (DataAccessException ex){
            return new ResponseEntity<>("Error al conectar con la base de datos", HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (RuntimeException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



}
