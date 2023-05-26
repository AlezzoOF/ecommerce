package com.idos.apk.backend.tienda.tatuajes.controller;


import com.idos.apk.backend.tienda.tatuajes.model.dto.producto.ProductoDTOIn;
import com.idos.apk.backend.tienda.tatuajes.model.dto.producto.ProductoDTOOut;
import com.idos.apk.backend.tienda.tatuajes.model.dto.producto.ProductoPageableResponse;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.ProductoService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/producto")
public class ProductoController {
    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;

    }

//    // Guardar la imagen en el servidor
//    String filename = StringUtils.cleanPath(file.getOriginalFilename());
//    Path path = Paths.get("./uploads");
//            if (!Files.exists(path)) {
//        Files.createDirectories(path);
//    }
//    String fileUrl = path.toAbsolutePath() + "/" + filename;
//            Files.copy(file.getInputStream(), path.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
//
//    // Crear y guardar el objeto Producto
//    DescripcionProducto tipoProducto = DescripcionProducto.valueOf(tipo);
//    Producto producto = new Producto(nombre, precio, fileUrl, tipoProducto);
//            service.save(producto);

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductoDTOOut> save(@RequestParam("nombre") String nombre,
                                               @RequestParam("descripcion") String descripcion,
                                               @RequestParam("precio") double precio,
                                               @RequestParam("cantidad") int cantidad,
                                               @RequestParam("tipo") String tipo,
                                               @RequestParam("file") MultipartFile file) {
        ProductoDTOIn producto = new ProductoDTOIn(nombre, descripcion, precio, cantidad, tipo);
        return new ResponseEntity<>(service.save(producto, file), HttpStatus.CREATED);
    }

    @GetMapping("/mostrar")
    public ResponseEntity<ProductoPageableResponse> findAll(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        return new ResponseEntity<>(service.getAll(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<ProductoDTOOut> findOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductoDTOOut> update(@RequestBody ProductoDTOIn producto, @PathVariable("id") Long id) {
        ProductoDTOOut p = service.update(producto, id);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return new ResponseEntity<>("Producto eliminado", HttpStatus.OK);
    }

    @GetMapping("/filtro/{filtro}")
    public ResponseEntity<ProductoPageableResponse> findAllByTipo(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @PathVariable("filtro") String filtro
    ) {
        return new ResponseEntity<>(service.getAllByTipo(pageNo, pageSize, filtro), HttpStatus.OK);
    }
}
