package com.idos.apk.backend.tienda.tatuajes.service;

import com.idos.apk.backend.tienda.tatuajes.exceptions.DataAllreadyTaken;
import com.idos.apk.backend.tienda.tatuajes.exceptions.TipoProductoNotFoundException;
import com.idos.apk.backend.tienda.tatuajes.model.TipoProducto;
import com.idos.apk.backend.tienda.tatuajes.repository.TipoProductoRepository;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.TipoProductoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoProductoServiceImp implements TipoProductoService {
    private final TipoProductoRepository repository;

    public TipoProductoServiceImp(TipoProductoRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public TipoProducto save(String string) throws DataAllreadyTaken {
        if (repository.existsByName(string)) {
            throw new DataAllreadyTaken("Allready exist");
        } else {
            TipoProducto tipo = new TipoProducto();
            tipo.setName(string);
            return repository.save(tipo);
        }
    }

    @Override
    public List<TipoProducto> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(String id) throws TipoProductoNotFoundException {
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
