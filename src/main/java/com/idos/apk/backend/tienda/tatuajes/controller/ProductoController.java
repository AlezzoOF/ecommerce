package com.idos.apk.backend.tienda.tatuajes.controller;


import com.idos.apk.backend.tienda.tatuajes.exceptions.DataAllreadyTaken;
import com.idos.apk.backend.tienda.tatuajes.exceptions.ProductoNotFoundException;
import com.idos.apk.backend.tienda.tatuajes.model.dto.producto.ProductoDTOIn;
import com.idos.apk.backend.tienda.tatuajes.model.dto.producto.ProductoDTOOut;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.ProductoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/producto")
public class ProductoController {
    private final ProductoService service;

    private final HttpServletRequest request;

    public ProductoController(ProductoService service, HttpServletRequest request) {
        this.service = service;

        this.request = request;
    }

    @PostMapping("/crear")
    public ResponseEntity save(@RequestParam("nombre") String nombre,
                               @RequestParam("descripcion") String descripcion,
                               @RequestParam("precio") double precio,
                               @RequestParam("cantidad") int cantidad,
                               @RequestParam("tipo") String tipo,
                               @RequestParam("file") MultipartFile file) throws DataAllreadyTaken {
        String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        ProductoDTOIn producto = new ProductoDTOIn(nombre, descripcion, precio, cantidad, tipo);


        return new ResponseEntity<>(service.save(producto, file), HttpStatus.CREATED);

    }

    @GetMapping("/mostrar")
    public ResponseEntity findAll(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {

        return new ResponseEntity<>(service.getAll(pageNo, pageSize), HttpStatus.OK);

    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity findOne(@PathVariable("id") String id) throws ProductoNotFoundException {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@RequestBody @Validated ProductoDTOIn producto, @PathVariable("id") String id) throws ProductoNotFoundException {
        return new ResponseEntity<>(service.update(producto, id), HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) throws ProductoNotFoundException {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/filtro")
    public ResponseEntity findAllByTipo(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam String filtro
    ) {

        return new ResponseEntity<>(service.getAllByTipo(pageNo, pageSize, filtro), HttpStatus.OK);

    }
}
