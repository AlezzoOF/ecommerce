package com.idos.apk.backend.tienda.tatuajes.controller;

import com.idos.apk.backend.tienda.tatuajes.exceptions.DataAllreadyTaken;
import com.idos.apk.backend.tienda.tatuajes.model.dto.user.AuthResponse;
import com.idos.apk.backend.tienda.tatuajes.model.dto.user.LoginDto;
import com.idos.apk.backend.tienda.tatuajes.model.dto.user.RegisterDto;
import com.idos.apk.backend.tienda.tatuajes.security.JWTGenerator;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.IBlackListService;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.UsuarioService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthController {
    private final UsuarioService service;
    private final AuthenticationManager authenticationManager;
    private final JWTGenerator generator;
    private final IBlackListService blackListService;


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated RegisterDto registerDto) throws DataAllreadyTaken {
        service.saveUser(registerDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/registerAdmin")
    public ResponseEntity registerAdmin(@RequestBody @Validated RegisterDto registerDto) throws DataAllreadyTaken {
        service.saveUserLikeAdmin(registerDto);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Validated LoginDto loginDto) throws UsernameNotFoundException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.userName(),
                        loginDto.pwd()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = generator.generateToken(authentication);
        String rol = service.valid(loginDto.userName()).getRol();
        return new ResponseEntity<>(new AuthResponse(token, "Bearer", rol), HttpStatus.OK);

    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout(@RequestBody String token){
        blackListService.save(token);
    }






}
