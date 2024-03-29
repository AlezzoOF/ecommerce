package com.idos.apk.backend.tienda.tatuajes.repository;

import com.idos.apk.backend.tienda.tatuajes.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Optional<Usuario> findByEmail(String email);

    Boolean existsByEmail(String email);
}
