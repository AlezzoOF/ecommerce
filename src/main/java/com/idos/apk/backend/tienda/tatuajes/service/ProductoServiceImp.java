package com.idos.apk.backend.tienda.tatuajes.service;

import com.idos.apk.backend.tienda.tatuajes.exceptions.DataAllreadyTaken;
import com.idos.apk.backend.tienda.tatuajes.exceptions.ProductoNotFoundException;
import com.idos.apk.backend.tienda.tatuajes.exceptions.TipoProductoNotFoundException;
import com.idos.apk.backend.tienda.tatuajes.model.Producto;
import com.idos.apk.backend.tienda.tatuajes.model.TipoProducto;
import com.idos.apk.backend.tienda.tatuajes.model.dto.producto.ProductoDTOIn;
import com.idos.apk.backend.tienda.tatuajes.model.dto.producto.ProductoDTOOut;
import com.idos.apk.backend.tienda.tatuajes.model.dto.producto.ProductoPageableResponse;
import com.idos.apk.backend.tienda.tatuajes.model.mapper.producto.ProductoDTOInToProducto;
import com.idos.apk.backend.tienda.tatuajes.model.mapper.producto.ProductoToProductoDTOOut;
import com.idos.apk.backend.tienda.tatuajes.repository.ProductoRepository;
import com.idos.apk.backend.tienda.tatuajes.repository.TipoProductoRepository;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.ProductoService;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.StorageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImp implements ProductoService {

    private final ProductoDTOInToProducto mapper;
    private final StorageService storageService;
    private final ProductoToProductoDTOOut mapper2;
    private final TipoProductoRepository tipoProductoRepository;

    private final ProductoRepository repository;

    public ProductoServiceImp(ProductoDTOInToProducto mapper, StorageService storageService, ProductoToProductoDTOOut mapper2, TipoProductoRepository tipoProductoRepository, ProductoRepository repository) {
        this.mapper = mapper;
        this.storageService = storageService;
        this.mapper2 = mapper2;
        this.tipoProductoRepository = tipoProductoRepository;
        this.repository = repository;
    }

    //Guardar un producto
    @Override
    @Transactional
    public ProductoDTOOut save(ProductoDTOIn objeto, MultipartFile file, HttpServletRequest request) throws DataAllreadyTaken {
        if (repository.existsByNombre(objeto.nombre())) {
            throw new DataAllreadyTaken("Name already exist");
        }
        Producto p = mapper.map(objeto);
        if (!file.isEmpty()) {
            String foto = storageService.store(file);
            String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
            String url = ServletUriComponentsBuilder
                    .fromHttpUrl(host)
                    .path("/files/")
                    .path(foto)
                    .toUriString();
            p.setImg(url);
        }

        TipoProducto tipo = tipoProductoRepository.findByName(objeto.tipo())
                .orElseGet(() -> tipoProductoRepository.save(new TipoProducto(objeto.tipo())));
        p.setTipo(tipo);
        repository.save(p);
        return mapper2.map(p);
    }

    //mostrar todos los productos
    @Override
    public ProductoPageableResponse getAll(int pageNo, int pageSize) {
        Pageable pagable = PageRequest.of(pageNo, pageSize);
        Page<Producto> lista = repository.findAll(pagable);
        List<ProductoDTOOut> content = lista.getContent().stream()
                .map(mapper2::map)
                .collect(Collectors.toList());

        ProductoPageableResponse response = new ProductoPageableResponse();
        response.setContent(content);
        response.setPageNo(lista.getNumber());
        response.setPageSize(lista.getSize());
        response.setTotalElements(lista.getTotalElements());
        response.setTotalPages(lista.getTotalPages());
        response.setLast(lista.isLast());

        return response;

    }

    //Buscar Producto por id
    @Override
    public ProductoDTOOut getById(String id) throws ProductoNotFoundException {
        Producto p = repository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException("Producto no encontrado"));
        return mapper2.map(p);
    }

    //Actualizar un producto
    @Override
    @Transactional
    public ProductoDTOOut update(ProductoDTOIn producto, String id, HttpServletRequest request) throws ProductoNotFoundException {
        Producto p = repository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException("Producto no pudo ser editado"));
        TipoProducto tipo = tipoProductoRepository.findByName(producto.tipo())
                .orElseGet(() -> tipoProductoRepository.save(new TipoProducto(producto.tipo())));
        p.setTipo(tipo);
        p.setId(id);
        p.setNombre(producto.nombre());
        p.setDescripcion(producto.descripcion());
        p.setCantidad(producto.cantidad());
        p.setPrecio(producto.precio());
        repository.save(p);
        return mapper2.map(p);
    }

    //Borrar producto x id
    @Override
    public void delete(String id, HttpServletRequest request) throws ProductoNotFoundException {
        String url = request.getRequestURL().toString().replace(request.getRequestURI(), "") + "/files/";
        Producto p = repository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException("No se pudo eliminar"));
        storageService.loadResource(p.getImg().replace(url,""));
        repository.deleteById(id);
    }

    //Buscar todos los productos filtrando x tipo
    @Override
    public ProductoPageableResponse getAllByTipo(int pageNo, int pageSize, String tipo) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        TipoProducto tipoProducto = tipoProductoRepository.findByName(tipo)
                .orElseThrow(() -> new TipoProductoNotFoundException("Tipo not found"));
        Page<Producto> lista = repository.findByTipo(pageable, tipoProducto);
        List<ProductoDTOOut> content = lista.getContent().stream()
                .map(mapper2::map)
                .collect(Collectors.toList());
        ProductoPageableResponse response = new ProductoPageableResponse();
        response.setContent(content);
        response.setPageNo(lista.getNumber());
        response.setPageSize(lista.getSize());
        response.setTotalElements(lista.getTotalElements());
        response.setTotalPages(lista.getTotalPages());
        response.setLast(lista.isLast());
        return response;
    }

    @Override
    public ProductoPageableResponse findAllByEnable(boolean bol, int pageNo, int pageSize) {
        Pageable pageable  = PageRequest.of(pageNo, pageSize);
        Page<Producto> lista = repository.findByEnable(pageable, bol);
        List<ProductoDTOOut> content = lista.getContent().stream()
                .map(mapper2::map)
                .collect(Collectors.toList());
        ProductoPageableResponse response = new ProductoPageableResponse();
        response.setContent(content);
        response.setPageNo(lista.getNumber());
        response.setPageSize(lista.getSize());
        response.setTotalElements(lista.getTotalElements());
        response.setTotalPages(lista.getTotalPages());
        response.setLast(lista.isLast());
        return response;
    }

    @Override
    public ProductoPageableResponse findByPrecioBetween(int pageNo, int pageSize, double precioMinimo, double precioMaximo) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Producto> lista = repository.findByPrecioBetween(pageable, precioMinimo, precioMaximo);
        List<ProductoDTOOut> content = lista.getContent().stream()
                .map(mapper2::map)
                .collect(Collectors.toList());
        ProductoPageableResponse response = new ProductoPageableResponse();
        response.setContent(content);
        response.setPageNo(lista.getNumber());
        response.setPageSize(lista.getSize());
        response.setTotalElements(lista.getTotalElements());
        response.setTotalPages(lista.getTotalPages());
        response.setLast(lista.isLast());

        return response;
    }


}
