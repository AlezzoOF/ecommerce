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
            throw new DataAllreadyTaken("Name allready exist");
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
                .orElseGet(() -> {
                    TipoProducto newTipo = new TipoProducto();
                    newTipo.setName(objeto.tipo());
                    return tipoProductoRepository.save(newTipo);
                });
        p.setTipo(tipo);
        repository.save(p);
        ProductoDTOOut enviar = mapper2.map(p);

        return enviar;

    }

    //mostrar todos los productos
    @Override
    public ProductoPageableResponse getAll(int pageNo, int pageSize) {
        Pageable pagable = PageRequest.of(pageNo, pageSize);
        Page<Producto> lista = repository.findAll(pagable);
        List<Producto> listOfProductos = lista.getContent();
        List<ProductoDTOOut> content = listOfProductos.stream().map(p -> mapper2.map(p)).collect(Collectors.toList());

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
        Producto p = repository.findById(id).orElseThrow(() -> new ProductoNotFoundException("Producto no encontrado"));
        return mapper2.map(p);
    }

    //Actualizar un producto
    @Override
    @Transactional
    public ProductoDTOOut update(ProductoDTOIn producto, String id, HttpServletRequest request) throws ProductoNotFoundException {
        Producto p = repository.findById(id).orElseThrow(() -> new ProductoNotFoundException("Producto no pudo ser editado"));
        TipoProducto tipo = tipoProductoRepository.findByName(producto.tipo())
                .orElseGet(() -> {
                    TipoProducto newTipo = new TipoProducto();
                    newTipo.setName(producto.tipo());
                    return tipoProductoRepository.save(newTipo);
                });
        p.setTipo(tipo);
        p.setId(id);
        p.setNombre(producto.nombre());
        p.setDescripcion(producto.descripcion());
        p.setCantidad(producto.cantidad());
        p.setPrecio(producto.precio());
        repository.save(p);
        ProductoDTOOut enviar = mapper2.map(p);
        return enviar;
    }

    //Borrar producto x id
    @Override
    public void delete(String id) throws ProductoNotFoundException {
        Producto p = repository.findById(id).orElseThrow(() -> new ProductoNotFoundException("No se pudo eliminar"));
        storageService.loadResource(p.getImg());
        repository.deleteById(id);
    }

    //Buscar todos los productos filtrando x tipo
    @Override
    public ProductoPageableResponse getAllByTipo(int pageNo, int pageSize, String tipo) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        TipoProducto tipoProducto = tipoProductoRepository.findByName(tipo).orElseThrow(() -> new TipoProductoNotFoundException("NotFoun Tipo"));
        Page<Producto> lista = repository.findAll(pageable);
        List<Producto> listOfProductos = lista.getContent();
        List<Producto> filtro = new ArrayList<>();
        for (Producto p :
                listOfProductos) {
            if (p.getTipo().getName().equals(tipoProducto.getName())) {
                filtro.add(p);
            }
        }
        List<ProductoDTOOut> content = filtro.stream().map(p -> mapper2.map(p)).collect(Collectors.toList());

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
        Pageable pagable = PageRequest.of(pageNo, pageSize);
        Page<Producto> lista = repository.findAll(pagable);
        List<Producto> listOfProductos = lista.getContent();
        List<Producto> filtro = new ArrayList<>();
        for (Producto p :
                listOfProductos) {
            if (p.isEnable()) {
                filtro.add(p);
            }
        }
        List<ProductoDTOOut> content = filtro.stream().map(p -> mapper2.map(p)).collect(Collectors.toList());

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
        Pageable pagable = PageRequest.of(pageNo, pageSize);
        Page<Producto> lista = repository.findAll(pagable);
        List<Producto> listOfProductos = lista.getContent();
        List<Producto> filtro = new ArrayList<>();
        for (Producto p :
                listOfProductos) {
            if (precioMinimo < p.getPrecio() && p.getPrecio() < precioMaximo) {
                filtro.add(p);
            }
        }
        List<ProductoDTOOut> content = filtro.stream().map(p -> mapper2.map(p)).collect(Collectors.toList());

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
