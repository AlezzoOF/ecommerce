package com.idos.apk.backend.tienda.tatuajes.service;

import com.idos.apk.backend.tienda.tatuajes.model.Usuario;
import com.idos.apk.backend.tienda.tatuajes.model.dto.user.RegisterDto;
import com.idos.apk.backend.tienda.tatuajes.model.mapper.user.RegisterDtoInToUser;
import com.idos.apk.backend.tienda.tatuajes.repository.UsuarioRepository;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UsuarioServiceImp implements UsuarioService {
    private final UsuarioRepository repository;


    private final RegisterDtoInToUser mapper;

    public UsuarioServiceImp(UsuarioRepository repository, RegisterDtoInToUser mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public void saveUser(RegisterDto registerDto) {
        Usuario user = mapper.map(registerDto);
        user.setRol("USER");
        repository.save(user);
//        System.out.println(user.getNombre());


    }

    @Override
    public void saveUserLikeAdmin(RegisterDto registerDto) {
        Usuario user = mapper.map(registerDto);
        user.setRol("ADMIN");
        repository.save(user);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return repository.findByEmail(email);
    }



    @Override
    public Boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }
}
