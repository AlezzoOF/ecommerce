package com.idos.apk.backend.tienda.tatuajes.service;

import com.idos.apk.backend.tienda.tatuajes.model.Usuario;
import com.idos.apk.backend.tienda.tatuajes.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    private void LoadData() {
        LoadRoles();
    }

    private void LoadRoles() {
        if (!usuarioRepository.existsByEmail("admin@admin.com")) {
            Usuario usuario = new Usuario();
            usuario.setNombre("admin");
            usuario.setApellido("admin");
            usuario.setDireccion("admin");
            usuario.setEmail("admin@admin.com");
            usuario.setPwd(passwordEncoder.encode("admin"));
            usuario.setRol("ADMIN");
            usuarioRepository.save(usuario);
        }
    }
}
