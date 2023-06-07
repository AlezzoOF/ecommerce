package com.idos.apk.backend.tienda.tatuajes.controller;

import com.idos.apk.backend.tienda.tatuajes.exceptions.DataAllreadyTaken;
import com.idos.apk.backend.tienda.tatuajes.exceptions.TipoProductoNotFoundException;
import com.idos.apk.backend.tienda.tatuajes.model.TipoProducto;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.TipoProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tp")
public class TipoProductoController {
    private final TipoProductoService service;

    public TipoProductoController(TipoProductoService service) {
        this.service = service;
    }

    @GetMapping("/show")
    public List<TipoProducto> show() {
        return service.findAll();
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestParam("string") String string) throws DataAllreadyTaken {
        return new ResponseEntity<>(service.save(string), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) throws TipoProductoNotFoundException {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
