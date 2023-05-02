package com.idos.apk.backend.tienda.tatuajes.controller;


import com.idos.apk.backend.tienda.tatuajes.model.Producto;
import com.idos.apk.backend.tienda.tatuajes.model.dto.ProductoDTOIn;
import com.idos.apk.backend.tienda.tatuajes.model.dto.ProductoDTOOut;
import com.idos.apk.backend.tienda.tatuajes.model.dto.ProductoPageableResponse;
import com.idos.apk.backend.tienda.tatuajes.model.mapper.producto.ProductoDTOInToProducto;
import com.idos.apk.backend.tienda.tatuajes.model.mapper.producto.ProductoToProductoDTOOut;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.ProductoService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {
  private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;

    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductoDTOOut> save(@RequestBody @NotNull ProductoDTOIn producto){
        return new ResponseEntity<>(service.save(producto), HttpStatus.CREATED);
    }
    @GetMapping("/mostrar")
    public ResponseEntity<ProductoPageableResponse> findAll(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        return new ResponseEntity<>(service.getAll(pageNo, pageSize), HttpStatus.OK);}

    @GetMapping("/buscar/{id}")
    public ResponseEntity<ProductoDTOOut> findOne(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<ProductoDTOOut> update(@RequestBody ProductoDTOIn producto, @PathVariable("id") Long id){
        ProductoDTOOut p = service.update(producto, id);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        service.delete(id);
        return new ResponseEntity<>("Producto eliminado", HttpStatus.OK);
    }
    }
