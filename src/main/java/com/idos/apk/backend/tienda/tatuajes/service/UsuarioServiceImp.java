package com.idos.apk.backend.tienda.tatuajes.service;

import com.idos.apk.backend.tienda.tatuajes.exceptions.DataAllreadyTaken;
import com.idos.apk.backend.tienda.tatuajes.model.Usuario;
import com.idos.apk.backend.tienda.tatuajes.model.dto.user.RegisterDto;
import com.idos.apk.backend.tienda.tatuajes.model.dto.user.UserDtoOut;
import com.idos.apk.backend.tienda.tatuajes.model.dto.user.UsuarioEdit;
import com.idos.apk.backend.tienda.tatuajes.model.mapper.user.RegisterDtoInToUser;
import com.idos.apk.backend.tienda.tatuajes.model.mapper.user.UserInToDtoOut;
import com.idos.apk.backend.tienda.tatuajes.repository.UsuarioRepository;
import com.idos.apk.backend.tienda.tatuajes.security.JWTGenerator;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImp implements UsuarioService {
    private final UsuarioRepository repository;
    private final UserInToDtoOut mapper2;

    private final RegisterDtoInToUser mapper;
    private final JWTGenerator generator;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImp(UsuarioRepository repository, UserInToDtoOut mapper2, RegisterDtoInToUser mapper, JWTGenerator generator, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.mapper2 = mapper2;
        this.mapper = mapper;
        this.generator = generator;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    @Transactional
    public void saveUser(RegisterDto registerDto) throws DataAllreadyTaken {
        validateEmailNotExist(registerDto.userName());
        Usuario user = mapper.map(registerDto);
        user.setRol("USER");
        repository.save(user);
    }

    @Override
    @Transactional
    public void saveUserLikeAdmin(RegisterDto registerDto) throws DataAllreadyTaken {
        validateEmailNotExist(registerDto.userName());
        Usuario user = mapper.map(registerDto);
        user.setRol("ADMIN");
        repository.save(user);
    }

    @Override
    public Usuario valid(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
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
        return repository.findAll()
                .stream()
                .map(mapper2::map)
                .collect(Collectors.toList());
    }

    @Override
    public UserDtoOut edit(UsuarioEdit edit, String id) throws UsernameNotFoundException {
        Usuario user = getUserById(id);
        user.setRol(edit.rol());
        user.setNombre(edit.nombre());
        user.setDireccion(edit.direccion());
        user.setApellido(edit.apellido());
        repository.save(user);
        return mapper2.map(user);

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
    public UserDtoOut findOneById(String id)throws UsernameNotFoundException {
        return mapper2.map(repository.findById(id).orElseThrow(()->new UsernameNotFoundException("User not found")));
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
