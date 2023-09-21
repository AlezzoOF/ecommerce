package com.idos.apk.backend.tienda.tatuajes.controller;


import com.idos.apk.backend.tienda.tatuajes.dto.producto.FiltroProducto;
import com.idos.apk.backend.tienda.tatuajes.dto.producto.ProductoInDto;
import com.idos.apk.backend.tienda.tatuajes.dto.producto.ProductoOutDto;
import com.idos.apk.backend.tienda.tatuajes.dto.producto.ProductoPageableResponse;
import com.idos.apk.backend.tienda.tatuajes.exceptions.DataAllreadyTaken;
import com.idos.apk.backend.tienda.tatuajes.exceptions.ProductoNotFoundException;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.ProductoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/producto")
@RequiredArgsConstructor
public class ProductoController {
    private final ProductoService service;

    private final HttpServletRequest request;

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductoOutDto save(@RequestParam("nombre") String nombre,
                               @RequestParam("descripcion") String descripcion,
                               @RequestParam("precio") double precio,
                               @RequestParam("cantidad") int cantidad,
                               @RequestParam("tipo") String tipo,
                               @RequestParam("file") MultipartFile file) throws DataAllreadyTaken {

        ProductoInDto producto = new ProductoInDto(nombre, descripcion, precio, cantidad, tipo);

        return service.save(producto, file, request);

    }

    @GetMapping("/mostrar")
    @ResponseStatus(HttpStatus.OK)
    public ProductoPageableResponse findAll(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {

        return service.getAll(pageNo, pageSize);

    }

    @GetMapping("/buscar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductoOutDto findOne(@PathVariable("id") String id) throws ProductoNotFoundException {
        return service.getById(id);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductoOutDto update(@RequestParam("nombre") String nombre,
                                 @RequestParam("descripcion") String descripcion,
                                 @RequestParam("precio") double precio,
                                 @RequestParam("cantidad") int cantidad,
                                 @RequestParam("tipo") String tipo,
                                 @RequestParam("file") MultipartFile file,
                                 @PathVariable("id")String id) throws DataAllreadyTaken {

        ProductoInDto producto = new ProductoInDto(nombre, descripcion, precio, cantidad, tipo);

        return service.update(producto, id, file, request);

    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") String id) throws ProductoNotFoundException {
        service.delete(id, request);
    }



    @PostMapping("/productos/filtrar")
    @ResponseStatus(HttpStatus.OK)
    public ProductoPageableResponse filtrarProductos(@RequestBody FiltroProducto filtro,
                                                     @RequestParam(defaultValue = "0") int pageNo,
                                                     @RequestParam(defaultValue = "10") int pageSize) {
        return service.filtrarProductos(filtro, pageNo, pageSize);
    }
}
