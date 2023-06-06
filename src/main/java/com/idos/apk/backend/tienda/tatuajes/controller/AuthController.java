package com.idos.apk.backend.tienda.tatuajes.controller;

import com.idos.apk.backend.tienda.tatuajes.exceptions.RequestException;
import com.idos.apk.backend.tienda.tatuajes.model.dto.user.AuthResponse;
import com.idos.apk.backend.tienda.tatuajes.model.dto.user.LoginDto;
import com.idos.apk.backend.tienda.tatuajes.model.dto.user.RegisterDto;
import com.idos.apk.backend.tienda.tatuajes.security.JWTGenerator;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    private final UsuarioService service;
    private final AuthenticationManager authenticationManager;
    private final JWTGenerator generator;

    public AuthController(UsuarioService service, AuthenticationManager authenticationManager, JWTGenerator generator) {
        this.service = service;
        this.authenticationManager = authenticationManager;
        this.generator = generator;
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated RegisterDto registerDto) {
        if (service.existsByEmail(registerDto.userName())) {
            throw new RequestException("Correo en uso", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        service.saveUser(registerDto);
//        System.out.println(registerDto.nombre());
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping("/registerAdmin")
    public ResponseEntity<String> registerAdmin(@RequestBody @Validated RegisterDto registerDto) {
        if (service.existsByEmail(registerDto.userName())) {
            throw new RequestException("Correo en uso", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        service.saveUserLikeAdmin(registerDto);
//        System.out.println(registerDto.nombre());
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Validated LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.userName(),
                        loginDto.pwd()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = generator.generateToken(authentication);
        String rol = service.valid(loginDto.userName()).getRol();
        return new ResponseEntity<>(new AuthResponse(token, "Bearer", rol), HttpStatus.OK);

    }
}
