package com.idos.apk.backend.tienda.tatuajes.service;

import com.idos.apk.backend.tienda.tatuajes.dto.producto.ProductoInDto;
import com.idos.apk.backend.tienda.tatuajes.dto.producto.ProductoOutDto;
import com.idos.apk.backend.tienda.tatuajes.exceptions.DataAllreadyTaken;
import com.idos.apk.backend.tienda.tatuajes.exceptions.ProductoNotFoundException;
import com.idos.apk.backend.tienda.tatuajes.exceptions.TipoProductoNotFoundException;
import com.idos.apk.backend.tienda.tatuajes.mapper.ProductoMapper;
import com.idos.apk.backend.tienda.tatuajes.model.Producto;
import com.idos.apk.backend.tienda.tatuajes.model.TipoProducto;
import com.idos.apk.backend.tienda.tatuajes.dto.producto.FiltroProducto;

import com.idos.apk.backend.tienda.tatuajes.dto.producto.ProductoPageableResponse;
import com.idos.apk.backend.tienda.tatuajes.repository.ProductoRepository;
import com.idos.apk.backend.tienda.tatuajes.repository.TipoProductoRepository;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.ProductoService;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.StorageService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoServiceImp implements ProductoService {


    private final StorageService storageService;
    private final ProductoMapper mapper;
    private final TipoProductoRepository tipoProductoRepository;
    private final ProductoRepository repository;

    //Guardar un producto
    @Override
    @Transactional
    public ProductoOutDto save(ProductoInDto objeto, MultipartFile file, HttpServletRequest request) throws DataAllreadyTaken {
        if (repository.existsByNombre(objeto.getNombre())) {
            throw new DataAllreadyTaken("Name already exist");
        }
        Producto p = mapper.productoInToProducto(objeto);
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

        TipoProducto tipo = tipoProductoRepository.findByName(objeto.getTipo())
                .orElseGet(() -> tipoProductoRepository.save(new TipoProducto(objeto.getTipo())));
        p.setTipo(tipo);
        repository.save(p);
        return mapper.productoToProductoDtoOut(p);
    }

    //mostrar todos los productos
    @Override
    public ProductoPageableResponse getAll(int pageNo, int pageSize) {
        Pageable pagable = PageRequest.of(pageNo, pageSize);
        Page<Producto> lista = repository.findAll(pagable);
        List<ProductoOutDto> content = lista.getContent().stream()
                .map(mapper::productoToProductoDtoOut)
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
    public ProductoOutDto getById(String id) throws ProductoNotFoundException {
        Producto p = repository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException("Producto no encontrado"));
        return mapper.productoToProductoDtoOut(p);
    }

    //Actualizar un producto
    @Override
    @Transactional
    public ProductoOutDto update(ProductoInDto producto, String id, HttpServletRequest request) throws ProductoNotFoundException {
        Producto p = repository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException("Producto no pudo ser editado"));
        TipoProducto tipo = tipoProductoRepository.findByName(producto.getTipo())
                .orElseGet(() -> tipoProductoRepository.save(new TipoProducto(producto.getTipo())));
        p.setTipo(tipo);
        p.setId(id);
        p.setNombre(producto.getNombre());
        p.setDescripcion(producto.getDescripcion());
        p.setCantidad(producto.getCantidad());
        p.setPrecio(producto.getPrecio());
        repository.save(p);
        return mapper.productoToProductoDtoOut(p);
    }

    //Borrar producto x id
    @Override
    public void delete(String id, HttpServletRequest request) throws ProductoNotFoundException {
        String url = request.getRequestURL().toString().replace(request.getRequestURI(), "") + "/files/";
        String p = repository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException("No se pudo eliminar")).getImg();
        storageService.loadResource(p.replace(url,""));
        repository.deleteById(id);
    }

    //Buscar todos los productos filtrando x tipo
    @Override
    public ProductoPageableResponse getAllByTipo(int pageNo, int pageSize, String tipo) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        TipoProducto tipoProducto = tipoProductoRepository.findByName(tipo)
                .orElseThrow(() -> new TipoProductoNotFoundException("Tipo not found"));
        Page<Producto> lista = repository.findByTipo(pageable, tipoProducto);
        List<ProductoOutDto> content = lista.getContent().stream()
                .map(mapper::productoToProductoDtoOut)
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
        List<ProductoOutDto> content = lista.getContent().stream()
                .map(mapper::productoToProductoDtoOut)
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
        List<ProductoOutDto> content = lista.getContent().stream()
                .map(mapper::productoToProductoDtoOut)
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

    ////prueba/////

    public ProductoPageableResponse filtrarProductos(FiltroProducto filtro, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Specification<Producto> spec = buildSpecification(filtro);
        Page<Producto> lista = repository.findAll(spec, pageable);
        List<ProductoOutDto> content = lista.getContent().stream()
                .map(mapper::productoToProductoDtoOut)
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

    private Specification<Producto> buildSpecification(FiltroProducto filtro) {
        return Specification.where(tipoEquals(filtro.getTipo()))
                .and(enableEquals(filtro.isEnable()))
                .and(precioBetween(filtro.getPrecioMinimo(), filtro.getPrecioMaximo()));
    }

    private Specification<Producto> tipoEquals(String tipo) {
        if (tipo != null && !tipo.isEmpty()) {
            return (root, query, builder) ->
                    builder.equal(root.get("tipo").get("name"), tipo);
        }
        return null;
    }

    private Specification<Producto> enableEquals(boolean enable) {
        return (root, query, builder) ->
                builder.equal(root.get("enable"), enable);
    }

    private Specification<Producto> precioBetween(double precioMinimo, double precioMaximo) {
        if (precioMinimo > 0.0 && precioMaximo > 0.0) {
            return (root, query, builder) ->
                    builder.between(root.get("precio"), precioMinimo, precioMaximo);
        } else {
            return null;
        }
    }


}
