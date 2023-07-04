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
    public TipoProducto save(String name) throws DataAllreadyTaken {
        if (repository.existsByName(name)) {
            throw new DataAllreadyTaken("Allready exist");
        }
        TipoProducto tipo = new TipoProducto();
        tipo.setName(name);
        return repository.save(tipo);
    }

    @Override
    public List<TipoProducto> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(String id) throws TipoProductoNotFoundException {
        if (!repository.existsById(id)) {
            throw new TipoProductoNotFoundException("Tipo de Producto not found");
        }
        repository.deleteById(id);

    }

    @Override
    public boolean ifExist(String name) {
        return repository.existsByName(name);
    }
}
