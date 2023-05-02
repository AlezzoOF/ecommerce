package com.idos.apk.backend.tienda.tatuajes.controller;


import com.idos.apk.backend.tienda.tatuajes.model.Producto;
import com.idos.apk.backend.tienda.tatuajes.model.dto.ProductoDTOIn;
import com.idos.apk.backend.tienda.tatuajes.model.dto.ProductoDTOOut;
import com.idos.apk.backend.tienda.tatuajes.model.mapper.producto.ProductoDTOInToProducto;
import com.idos.apk.backend.tienda.tatuajes.model.mapper.producto.ProductoToProductoDTOOut;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.ProductoService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {
  private final ProductoService service;
  private final ProductoToProductoDTOOut mapper;
  private final ProductoDTOInToProducto mapper2;

    public ProductoController(ProductoService service, ProductoToProductoDTOOut mapper, ProductoDTOInToProducto mapper2) {
        this.service = service;
        this.mapper = mapper;
        this.mapper2 = mapper2;
    }

    @PostMapping("/crear")
    public ResponseEntity<String> save(@RequestBody @NotNull ProductoDTOIn producto){
        if (producto.nombre().isBlank()||producto.descripcion().isBlank()||producto.img().isBlank()||producto.tipo().isBlank()||producto.precio()<=0||producto.cantidad()<0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos erroneos");
        }
        Producto p = mapper2.map(producto);
        service.save(p);
        return ResponseEntity.status(HttpStatus.CREATED).body("Producto creado");
    }
    @GetMapping("/mostrar")
    public List<ProductoDTOOut> findAll(){
        List<Producto> lista = service.findAll();
        List<ProductoDTOOut> enviar = null;
        for (Producto p:
             lista) {
            enviar.add(mapper.map(p));
        }
        return enviar;

    }
    @PatchMapping("/update")
    public ResponseEntity<String> update(@RequestBody ProductoDTOIn producto){
        Producto p = service.get(producto.id()).get();
        Producto nuevo = mapper2.map(producto);
        nuevo.setId(producto.id());
        service.update(nuevo);
        return ResponseEntity.status(HttpStatus.OK).body("Editado");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Eliminado");
    }
}
