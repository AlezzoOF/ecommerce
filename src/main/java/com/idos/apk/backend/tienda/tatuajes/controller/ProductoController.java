package com.idos.apk.backend.tienda.tatuajes.controller;


import com.idos.apk.backend.tienda.tatuajes.dto.producto.ProductoInDto;
import com.idos.apk.backend.tienda.tatuajes.exceptions.DataAllreadyTaken;
import com.idos.apk.backend.tienda.tatuajes.exceptions.ProductoNotFoundException;
import com.idos.apk.backend.tienda.tatuajes.dto.producto.FiltroProducto;
import com.idos.apk.backend.tienda.tatuajes.dto.producto.ProductoPageableResponse;
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

        ProductoInDto producto = new ProductoInDto(nombre, descripcion, precio, cantidad, tipo);

        return new ResponseEntity<>(service.save(producto, file, request), HttpStatus.CREATED);

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
    public ResponseEntity update(@RequestBody @Validated ProductoInDto producto, @PathVariable("id") String id) throws ProductoNotFoundException {
        return new ResponseEntity<>(service.update(producto, id, request), HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) throws ProductoNotFoundException {
        service.delete(id, request);
        return new ResponseEntity<>(HttpStatus.OK);

    }


    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<ProductoPageableResponse> getProductosByTipo(
            @PathVariable String tipo,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        ProductoPageableResponse response = service.getAllByTipo(pageNo, pageSize, tipo);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/enable/{bol}")
    public ResponseEntity<ProductoPageableResponse> getProductosByEnable(
            @PathVariable boolean bol,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        ProductoPageableResponse response = service.findAllByEnable(bol, pageNo, pageSize);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/precio")
    public ResponseEntity<ProductoPageableResponse> getProductosByPrecioBetween(
            @RequestParam double precioMinimo,
            @RequestParam double precioMaximo,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        ProductoPageableResponse response = service.findByPrecioBetween(pageNo, pageSize, precioMinimo, precioMaximo);
        return ResponseEntity.ok(response);
    }

    //////Prueba/////
    @PostMapping("/productos/filtrar")
    public ProductoPageableResponse filtrarProductos(@RequestBody FiltroProducto filtro,
                                                     @RequestParam(defaultValue = "0") int pageNo,
                                                     @RequestParam(defaultValue = "10") int pageSize) {
        return service.filtrarProductos(filtro, pageNo, pageSize);
    }
}
