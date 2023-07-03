package com.idos.apk.backend.tienda.tatuajes.service;

import com.idos.apk.backend.tienda.tatuajes.exceptions.DataAllreadyTaken;
import com.idos.apk.backend.tienda.tatuajes.model.Usuario;
import com.idos.apk.backend.tienda.tatuajes.model.dto.user.RegisterDto;
import com.idos.apk.backend.tienda.tatuajes.model.dto.user.UserDtoOut;
import com.idos.apk.backend.tienda.tatuajes.model.mapper.user.RegisterDtoInToUser;
import com.idos.apk.backend.tienda.tatuajes.model.mapper.user.UserInToDtoOut;
import com.idos.apk.backend.tienda.tatuajes.repository.UsuarioRepository;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImp implements UsuarioService {
    private final UsuarioRepository repository;
    private final UserInToDtoOut mapper2;

    private final RegisterDtoInToUser mapper;

    public UsuarioServiceImp(UsuarioRepository repository, UserInToDtoOut mapper2, RegisterDtoInToUser mapper) {
        this.repository = repository;
        this.mapper2 = mapper2;
        this.mapper = mapper;
    }


    @Override
    @Transactional
    public void saveUser(RegisterDto registerDto) throws DataAllreadyTaken {
        if (repository.existsByEmail(registerDto.userName())) {
            throw new DataAllreadyTaken("User already exist");
        }
        Usuario user = mapper.map(registerDto);
        user.setRol("USER");
        repository.save(user);
//        System.out.println(user.getNombre());
    }

    @Override
    @Transactional
    public void saveUserLikeAdmin(RegisterDto registerDto) throws DataAllreadyTaken {
        if (repository.existsByEmail(registerDto.userName())) {
            throw new DataAllreadyTaken("User already exist");
        }
        Usuario user = mapper.map(registerDto);
        user.setRol("ADMIN");
        repository.save(user);
    }

    @Override
    public Usuario valid(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

    @Override
    public UserDtoOut findByEmail(String email) throws UsernameNotFoundException {
        return mapper2.map(repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found")));
    }


    @Override
    public Boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public List<UserDtoOut> getAll() {
        return repository.findAll().stream().map(mapper2::map).collect(Collectors.toList());
    }

    @Override
    public UserDtoOut editRol(String id, String rol) throws UsernameNotFoundException {
        if (repository.findById(id).isEmpty()) {
            throw new UsernameNotFoundException("Usuario no existente");
        }
        Usuario user = repository.findById(id).get();
        user.setRol(rol);
        repository.save(user);
        return mapper2.map(user);

    }

}
