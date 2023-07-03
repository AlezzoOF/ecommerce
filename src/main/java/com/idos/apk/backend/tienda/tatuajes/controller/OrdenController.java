package com.idos.apk.backend.tienda.tatuajes.controller;

import com.idos.apk.backend.tienda.tatuajes.exceptions.OrdenNotFoundException;
import com.idos.apk.backend.tienda.tatuajes.exceptions.ProductoNotFoundException;
import com.idos.apk.backend.tienda.tatuajes.model.dto.detalleorden.DetalleOrdenDto;
import com.idos.apk.backend.tienda.tatuajes.model.dto.orden.OrdenDtoIn;
import com.idos.apk.backend.tienda.tatuajes.model.dto.orden.OrdenPorAgno;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.DetalleOrdenService;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.OrdenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    public ResponseEntity crear(@RequestBody @Validated OrdenDtoIn orden) throws UsernameNotFoundException, ProductoNotFoundException {
        String id = service.save(orden).numero();
        for (DetalleOrdenDto detalle : orden.lista()) {
            detalleOrdenService.save(detalle, id);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @GetMapping("/mostrar")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity mostrar(@RequestParam String token) throws UsernameNotFoundException {
        return new ResponseEntity<>(service.getAllByUser(token), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") String id) throws OrdenNotFoundException {
        service.delete(id);
    }

    @GetMapping("/tabla")
    @ResponseStatus(HttpStatus.OK)
    public OrdenPorAgno tabla(){
        String agno = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy"));
        return service.filtroMes(agno);
    }
}
