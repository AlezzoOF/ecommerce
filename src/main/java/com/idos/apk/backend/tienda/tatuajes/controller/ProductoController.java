package com.idos.apk.backend.tienda.tatuajes.controller;


import com.idos.apk.backend.tienda.tatuajes.exceptions.RequestException;
import com.idos.apk.backend.tienda.tatuajes.model.dto.producto.ProductoDTOIn;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.ProductoService;
import org.springframework.core.io.Resource;
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
    public ResponseEntity save(@RequestParam("nombre")  String nombre,
                               @RequestParam("descripcion")  String descripcion,
                               @RequestParam("precio") double precio,
                               @RequestParam("cantidad")  int cantidad,
                               @RequestParam("tipo") String tipo,
                               @RequestParam("file")  MultipartFile file) {
        ProductoDTOIn producto = new ProductoDTOIn(nombre, descripcion, precio, cantidad, tipo);
        try {

            return new ResponseEntity<>(service.save(producto, file), HttpStatus.CREATED);
        }
        catch (RuntimeException ex) {
            throw new RequestException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/foto/{id}")
    public Resource foto(@PathVariable("id") String id) {
        return service.getFoto(id);
    }

    @GetMapping("/mostrar")
    public ResponseEntity findAll(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        try {
            return new ResponseEntity<>(service.getAll(pageNo, pageSize), HttpStatus.OK);
        } catch (RuntimeException ex) {
            throw new RequestException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity findOne(@PathVariable("id") String id) {
        try {
            return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
        }catch (RuntimeException ex) {
            throw new RequestException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@RequestBody @Validated ProductoDTOIn producto, @PathVariable("id") String id) {
        try {
            return new ResponseEntity<>(service.update(producto, id), HttpStatus.OK);
        }catch (RuntimeException ex) {
            throw new RequestException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        try {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (RuntimeException ex) {
            throw new RequestException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/filtro")
    public ResponseEntity findAllByTipo(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam String filtro
    ) {
        try {
            return new ResponseEntity<>(service.getAllByTipo(pageNo, pageSize, filtro), HttpStatus.OK);
        } catch (RuntimeException ex) {
            throw new RequestException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
