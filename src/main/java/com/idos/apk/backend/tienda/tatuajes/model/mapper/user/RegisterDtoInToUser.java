package com.idos.apk.backend.tienda.tatuajes.model.mapper.user;

import com.idos.apk.backend.tienda.tatuajes.model.Usuario;
import com.idos.apk.backend.tienda.tatuajes.model.dto.user.RegisterDto;
import com.idos.apk.backend.tienda.tatuajes.model.mapper.Mapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RegisterDtoInToUser implements Mapper<RegisterDto, Usuario> {
    private final PasswordEncoder passwordEncoder;

    public RegisterDtoInToUser(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Usuario map(RegisterDto in) {
        Usuario user = new Usuario();
        user.setNombre(in.nombre());
        user.setApellido(in.apellido());
        user.setDireccion(in.direccion());
        user.setEmail(in.userName());
        user.setPwd(passwordEncoder.encode(in.pwd()));

        return user;
    }
}
