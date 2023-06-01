package com.idos.apk.backend.tienda.tatuajes.controller;

import com.idos.apk.backend.tienda.tatuajes.model.dto.detalleorden.DetalleOrdenDto;
import com.idos.apk.backend.tienda.tatuajes.model.dto.orden.OrdenDtoIn;
import com.idos.apk.backend.tienda.tatuajes.model.dto.orden.OrdenDtoOut;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.DetalleOrdenService;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.OrdenService;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.List;

@RestController
@RequestMapping("/orden")
@CrossOrigin(origins = "*")
public class OrdenController {

    private final OrdenService service;
    private final DetalleOrdenService detalleOrdenService;


    public OrdenController(OrdenService service, DetalleOrdenService detalleOrdenService) {
        this.service = service;
        this.detalleOrdenService = detalleOrdenService;
    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity crear(@RequestBody @Validated OrdenDtoIn orden) {
        try{
            String id = service.save(orden).numero();
            for (DetalleOrdenDto detalle: orden.lista()){
                detalleOrdenService.save(detalle, id);
            }
            //guardar detalle
            return new ResponseEntity<>("Orden creada", HttpStatus.CREATED);
        }catch (DataAccessException ex){
            return new ResponseEntity<>("Erro al conectar con la base de datos", HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (RuntimeException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/mostrar")
    public ResponseEntity mostrar(@RequestParam String token) {
        try{
            return new ResponseEntity<>(service.getAllByUser(token), HttpStatus.OK);
        }catch (DataAccessException ex){
            return new ResponseEntity<>("Erro al conectar con la base de datos", HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (RuntimeException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id")String id) {
        try{
            service.delete(id);
            return new ResponseEntity<>("Eliminado", HttpStatus.OK);
        }catch (DataAccessException ex){
            return new ResponseEntity<>("Erro al conectar con la base de datos", HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (RuntimeException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
