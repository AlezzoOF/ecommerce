package com.idos.apk.backend.tienda.tatuajes.service;

import com.idos.apk.backend.tienda.tatuajes.exceptions.ProductoNotFoundException;
import com.idos.apk.backend.tienda.tatuajes.model.Producto;
import com.idos.apk.backend.tienda.tatuajes.model.dto.ProductoDTOIn;
import com.idos.apk.backend.tienda.tatuajes.model.dto.ProductoDTOOut;
import com.idos.apk.backend.tienda.tatuajes.model.dto.ProductoPageableResponse;
import com.idos.apk.backend.tienda.tatuajes.model.mapper.producto.ProductoDTOInToProducto;
import com.idos.apk.backend.tienda.tatuajes.model.mapper.producto.ProductoToProductoDTOOut;
import com.idos.apk.backend.tienda.tatuajes.repository.ProductoRepository;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.ProductoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImp implements ProductoService {

    private final ProductoDTOInToProducto mapper;
    private final ProductoToProductoDTOOut mapper2;

    private final ProductoRepository repository;

    public ProductoServiceImp(ProductoDTOInToProducto mapper, ProductoToProductoDTOOut mapper2, ProductoRepository repository) {
        this.mapper = mapper;
        this.mapper2 = mapper2;
        this.repository = repository;
    }


    @Override
    public ProductoDTOOut save(ProductoDTOIn objeto) {
        Producto p = mapper.map(objeto);
        repository.save(p);
        ProductoDTOOut enviar = mapper2.map(p);
        return enviar;

    }

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

    @Override
    public ProductoDTOOut getById(Long id) {
        Producto p = repository.findById(id).orElseThrow(()->new ProductoNotFoundException("Producto no encontrado"));
        return mapper2.map(p);
    }

    @Override
    public ProductoDTOOut update(ProductoDTOIn producto, Long id) {
        Producto p = repository.findById(id).orElseThrow(()-> new ProductoNotFoundException("Producto no pudo ser editado"));
        Producto guardar = mapper.map(producto);
        guardar.setId(p.getId());
        repository.save(guardar);
        return mapper2.map(guardar);
    }

    @Override
    public void delete(Long id) {
        Producto p = repository.findById(id).orElseThrow(()-> new ProductoNotFoundException("No se pudo eliminar"));
        repository.deleteById(id);
    }


}
