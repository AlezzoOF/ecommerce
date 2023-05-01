package com.idos.apk.backend.tienda.tatuajes.service;

import com.idos.apk.backend.tienda.tatuajes.model.Producto;
import com.idos.apk.backend.tienda.tatuajes.repository.ProductoRepository;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.ProductoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImp implements ProductoService {

    private final ProductoRepository repository;

    public ProductoServiceImp(ProductoRepository repository) {
        this.repository = repository;
    }


    @Override
    public Producto save(Producto objeto) {
        return repository.save(objeto);
    }

    @Override
    public Optional<Producto> get(Long id) {
        return repository.findById(id);
    }

    @Override
    public void update(Producto objeto) {
        repository.save(objeto);

    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);

    }

    @Override
    public List<Producto> findAll() {
        return repository.findAll();
    }
}
