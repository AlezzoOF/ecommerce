package com.idos.apk.backend.tienda.tatuajes.controller;

import com.idos.apk.backend.tienda.tatuajes.model.dto.user.UserDtoOut;
import com.idos.apk.backend.tienda.tatuajes.model.dto.user.UsuarioEdit;
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

    @PutMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDtoOut editRol(@RequestBody UsuarioEdit edit, @PathVariable String id) throws UsernameNotFoundException {
        return service.edit(edit, id);
    }

    @DeleteMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable String id)throws UsernameNotFoundException{
        service.deleteById(id);
    }


}
