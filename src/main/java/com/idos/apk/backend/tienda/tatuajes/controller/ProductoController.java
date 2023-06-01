package com.idos.apk.backend.tienda.tatuajes.controller;


import com.idos.apk.backend.tienda.tatuajes.model.dto.producto.ProductoDTOIn;

import com.idos.apk.backend.tienda.tatuajes.service.interfaces.ProductoService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/producto")
public class ProductoController {
    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;

    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity save(@RequestParam("nombre")  @NotBlank String nombre,
                                               @RequestParam("descripcion") @NotBlank String descripcion,
                                               @RequestParam("precio") @Min(0) double precio,
                                               @RequestParam("cantidad") @Min(0) int cantidad,
                                               @RequestParam("tipo") @NotBlank String tipo,
                                               @RequestParam("file") @NotNull MultipartFile file) {
        ProductoDTOIn producto = new ProductoDTOIn(nombre, descripcion, precio, cantidad, tipo);
        try{
            service.save(producto, file);
            return new ResponseEntity<>("Producto creado", HttpStatus.CREATED);
        }catch (DataAccessException exception){
            return new ResponseEntity<>("Error en la conexion con base de datos", HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (RuntimeException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/foto/{id}")
    public Resource foto(@PathVariable("id") String id){
        return service.getFoto(id);
    }

    @GetMapping("/mostrar")
    public ResponseEntity findAll(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        try {
            return new ResponseEntity<>(service.getAll(pageNo, pageSize), HttpStatus.OK);
        }catch (DataAccessException ex){
            return new ResponseEntity<>("Error en la conexion con base de datos", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity findOne(@PathVariable("id") String id) {
        try{
            return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
        }catch (DataAccessException ex){
            return new ResponseEntity<>("Error en la conexion con base de datos", HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (RuntimeException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@RequestBody @Validated ProductoDTOIn producto, @PathVariable("id") String id) {
        try{
            service.update(producto, id);
            return new ResponseEntity<>("Producto editado", HttpStatus.OK);
        }catch (DataAccessException ex){
            return new ResponseEntity<>("Error en la conexion con base de datos", HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (RuntimeException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        try{
            service.delete(id);
            return new ResponseEntity<>("Producto eliminado", HttpStatus.OK);
        }catch (DataAccessException ex){
            return new ResponseEntity<>("Error en la conexion con base de datos", HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (RuntimeException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/filtro")
    public ResponseEntity findAllByTipo(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam String filtro
    ) {
        try{
            return new ResponseEntity<>(service.getAllByTipo(pageNo, pageSize, filtro), HttpStatus.OK);
        }catch (RuntimeException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
