package com.idos.apk.backend.tienda.tatuajes.controller;

import com.idos.apk.backend.tienda.tatuajes.exceptions.DataAllreadyTaken;
import com.idos.apk.backend.tienda.tatuajes.exceptions.TipoProductoNotFoundException;
import com.idos.apk.backend.tienda.tatuajes.model.TipoProducto;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.TipoProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tp")
@RequiredArgsConstructor
public class TipoProductoController {
    private final TipoProductoService service;

    @GetMapping("/show")
    @ResponseStatus(HttpStatus.OK)
    public List<TipoProducto> show() {
        return service.findAll();
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public TipoProducto save(@RequestParam("string") String string) throws DataAllreadyTaken {
        return service.save(string);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") String id) throws TipoProductoNotFoundException {
        service.delete(id);
    }
}
