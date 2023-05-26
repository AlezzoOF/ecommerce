package com.idos.apk.backend.tienda.tatuajes.service;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImp implements ProductoService {

    private final ProductoDTOInToProducto mapper;
    private final ProductoToProductoDTOOut mapper2;
    private final TipoProductoRepository tipoProductoRepository;

    private final ProductoRepository repository;

    public ProductoServiceImp(ProductoDTOInToProducto mapper, ProductoToProductoDTOOut mapper2, TipoProductoRepository tipoProductoRepository, ProductoRepository repository) {
        this.mapper = mapper;
        this.mapper2 = mapper2;
        this.tipoProductoRepository = tipoProductoRepository;
        this.repository = repository;
    }

    //Guardar un producto
    @Override
    public ProductoDTOOut save(ProductoDTOIn objeto) {
        Producto p = mapper.map(objeto);
        if (tipoProductoRepository.existsByName(objeto.tipo())){
            p.setTipo(tipoProductoRepository.findByName(objeto.tipo()).get());
        }else{
            TipoProducto tipo = new TipoProducto();
            tipo.setName(objeto.tipo());
            p.setTipo(tipo);
            tipoProductoRepository.save(tipo);
        }
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
    public ProductoDTOOut getById(Long id) {
        Producto p = repository.findById(id).orElseThrow(() -> new ProductoNotFoundException("Producto no encontrado"));
        return mapper2.map(p);
    }

    //Actualizar un producto
    @Override
    public ProductoDTOOut update(ProductoDTOIn producto, Long id) {
        Producto p = repository.findById(id).orElseThrow(() -> new ProductoNotFoundException("Producto no pudo ser editado"));
        Producto guardar = mapper.map(producto);
        guardar.setId(p.getId());
        repository.save(guardar);
        return mapper2.map(guardar);
    }

    //Borrar producto x id
    @Override
    public void delete(Long id) {
        Producto p = repository.findById(id).orElseThrow(() -> new ProductoNotFoundException("No se pudo eliminar"));
        repository.deleteById(id);
    }

    //Buscar todos los productos filtrando x tipo
    @Override
    public ProductoPageableResponse getAllByTipo(int pageNo, int pageSize, String tipo) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        TipoProducto tipoProducto = tipoProductoRepository.findByName(tipo).get();
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


}
