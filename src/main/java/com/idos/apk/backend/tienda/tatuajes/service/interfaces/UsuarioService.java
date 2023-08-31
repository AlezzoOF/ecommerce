package com.idos.apk.backend.tienda.tatuajes.service.interfaces;

import com.idos.apk.backend.tienda.tatuajes.dto.user.RegisterDto;
import com.idos.apk.backend.tienda.tatuajes.dto.user.UserEditDto;
import com.idos.apk.backend.tienda.tatuajes.dto.user.UserOutDto;
import com.idos.apk.backend.tienda.tatuajes.model.Usuario;

import java.util.List;

public interface UsuarioService {
    void saveUser(RegisterDto registerDto);

    void saveUserLikeAdmin(RegisterDto registerDto);

    Usuario valid(String email);

    UserOutDto findByEmail(String email);


    Boolean existsByEmail(String email);

    List<UserOutDto> getAll();

    UserOutDto edit(UserEditDto edit, String id);

    void deleteById(String id);
    void deleteByToken(String token);

    UserOutDto findOneById(String id);
}
