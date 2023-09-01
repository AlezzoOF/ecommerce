package com.idos.apk.backend.tienda.tatuajes.service;

import com.idos.apk.backend.tienda.tatuajes.dto.user.RegisterDto;
import com.idos.apk.backend.tienda.tatuajes.dto.user.UserEditDto;
import com.idos.apk.backend.tienda.tatuajes.dto.user.UserOutDto;
import com.idos.apk.backend.tienda.tatuajes.exceptions.DataAllreadyTaken;
import com.idos.apk.backend.tienda.tatuajes.mapper.UserMapper;
import com.idos.apk.backend.tienda.tatuajes.model.Usuario;
import com.idos.apk.backend.tienda.tatuajes.repository.UsuarioRepository;
import com.idos.apk.backend.tienda.tatuajes.security.JWTGenerator;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.UsuarioService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImp implements UsuarioService {
    private final UsuarioRepository repository;
    private final UserMapper mapper;
    private final JWTGenerator generator;
    private final PasswordEncoder passwordEncoder;



    @Override
    @Transactional
    public void saveUser(RegisterDto registerDto) throws DataAllreadyTaken {
        validateEmailNotExist(registerDto.getUserName());
        Usuario user = mapper.registerDtoToUser(registerDto);
        user.setRol("USER");
        repository.save(user);
    }

    @Override
    @Transactional
    public void saveUserLikeAdmin(RegisterDto registerDto) throws DataAllreadyTaken {
        validateEmailNotExist(registerDto.getUserName());
        Usuario user = mapper.registerDtoToUser(registerDto);
        user.setRol("ADMIN");
        repository.save(user);
    }

    @Override
    public Usuario valid(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

    @Override
    public UserOutDto findByEmail(String email) throws UsernameNotFoundException {
        return mapper.userToUserOut(repository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User not found")));
    }


    @Override
    public Boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public List<UserOutDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::userToUserOut)
                .collect(Collectors.toList());
    }

    @Override
    public UserOutDto edit(UserEditDto edit, String id) throws UsernameNotFoundException {
        Usuario user = getUserById(id);
        user.setRol(edit.getRol());
        user.setNombre(edit.getNombre());
        user.setDireccion(edit.getDireccion());
        user.setApellido(edit.getApellido());
        repository.save(user);
        return mapper.userToUserOut(user);

    }

    @Override
    public void deleteById(String id)throws UsernameNotFoundException {
        Usuario user = getUserById(id);
        repository.deleteById(user.getId());
    }

    @Override
    public void deleteByToken(String token)throws UsernameNotFoundException {
        String email = generator.getUsernameFromJwt(token);
        Usuario user = getUserByEmail(email);
        repository.deleteById(user.getId());

    }

    @Override
    public UserOutDto findOneById(String id)throws UsernameNotFoundException {
        return mapper.userToUserOut(repository.findById(id).orElseThrow(()->new UsernameNotFoundException("User not found")));
    }

    //Metodos auxiliares

    private void validateEmailNotExist(String email) throws DataAllreadyTaken {
        if (repository.existsByEmail(email)) {
            throw new DataAllreadyTaken("User already exists");
        }
    }

    private Usuario getUserById(String id) throws UsernameNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    private Usuario getUserByEmail(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}
