package com.idos.apk.backend.tienda.tatuajes.controller;

import com.idos.apk.backend.tienda.tatuajes.dto.user.UserEditDto;
import com.idos.apk.backend.tienda.tatuajes.dto.user.UserOutDto;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public List<UserOutDto> getAll() {
        return service.getAll();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserOutDto editRol(@RequestBody UserEditDto edit, @PathVariable String id) throws UsernameNotFoundException {
        return service.edit(edit, id);
    }

    @DeleteMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable String id)throws UsernameNotFoundException{
        service.deleteById(id);
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserOutDto findOneById(@PathVariable String id)throws UsernameNotFoundException{
       return service.findOneById(id);
    }
}
