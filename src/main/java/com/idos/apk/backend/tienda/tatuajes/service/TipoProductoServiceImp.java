package com.idos.apk.backend.tienda.tatuajes.service;

import com.idos.apk.backend.tienda.tatuajes.exceptions.TipoProductoAllReadyExist;
import com.idos.apk.backend.tienda.tatuajes.exceptions.TipoProductoNotFoundException;
import com.idos.apk.backend.tienda.tatuajes.model.TipoProducto;
import com.idos.apk.backend.tienda.tatuajes.repository.TipoProductoRepository;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.TipoProductoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoProductoServiceImp implements TipoProductoService {
    private final TipoProductoRepository repository;

    public TipoProductoServiceImp(TipoProductoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(String string) {
        if (!repository.existsByName(string)) {
            TipoProducto tipo = new TipoProducto();
            tipo.setName(string);
            repository.save(tipo);
        } else {
            throw new TipoProductoAllReadyExist("Tipo de producto ya existente");
        }
    }

    @Override
    public List<TipoProducto> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new TipoProductoNotFoundException("TIpo de Producto no encontrado");
        }

    }

    @Override
    public boolean ifExist(String s) {
        return repository.existsByName(s);
    }
}
