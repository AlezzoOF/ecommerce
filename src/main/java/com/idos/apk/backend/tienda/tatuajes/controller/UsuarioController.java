package com.idos.apk.backend.tienda.tatuajes.controller;

import com.idos.apk.backend.tienda.tatuajes.model.dto.user.UserDtoOut;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDtoOut> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}/rol/{rol}")
    @ResponseStatus(HttpStatus.OK)
    public UserDtoOut editRol(@PathVariable String id, @PathVariable String rol) throws UsernameNotFoundException {
        return service.editRol(id, rol);
    }
}
