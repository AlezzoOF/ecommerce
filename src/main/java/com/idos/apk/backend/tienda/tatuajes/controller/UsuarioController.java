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
//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserOutDto> getAll() {
        return service.getAll();
    }

//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserOutDto editRol(@RequestBody UserEditDto edit, @PathVariable String id) throws UsernameNotFoundException {
        return service.edit(edit, id);
    }
//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable String id)throws UsernameNotFoundException{
        service.deleteById(id);
    }
//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserOutDto findOneById(@PathVariable String id)throws UsernameNotFoundException{
       return service.findOneById(id);
    }
}
