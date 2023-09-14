package com.idos.apk.backend.tienda.tatuajes.controller;

import com.idos.apk.backend.tienda.tatuajes.dto.user.AuthResponse;
import com.idos.apk.backend.tienda.tatuajes.dto.user.LoginDto;
import com.idos.apk.backend.tienda.tatuajes.dto.user.RegisterDto;
import com.idos.apk.backend.tienda.tatuajes.exceptions.DataAllreadyTaken;
import com.idos.apk.backend.tienda.tatuajes.model.BlacklistedToken;
import com.idos.apk.backend.tienda.tatuajes.model.Usuario;
import com.idos.apk.backend.tienda.tatuajes.security.JWTGenerator;
import com.idos.apk.backend.tienda.tatuajes.security.SecurityConstants;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.IBlackListService;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


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
    @ResponseStatus(HttpStatus.OK)
    public void register(@RequestBody @Validated RegisterDto registerDto) throws DataAllreadyTaken {
        service.saveUser(registerDto);
    }

    @PostMapping("/registerAdmin")
    @ResponseStatus(HttpStatus.OK)
    public void registerAdmin(@RequestBody @Validated RegisterDto registerDto) throws DataAllreadyTaken {
        service.saveUserLikeAdmin(registerDto);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse login(@RequestBody @Validated LoginDto loginDto) throws UsernameNotFoundException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUserName(),
                        loginDto.getPwd()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = generator.generateToken(authentication);
        Usuario user = service.valid(loginDto.getUserName());
        return AuthResponse.builder()
                .token(token)
                .tokenType("Bearer")
                .rol(user.getRol())
                .username(user.getEmail())
                .name(user.getNombre())
                .last_name(user.getApellido())
                .id(user.getId()).build();

    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout(@RequestBody String token){
        BlacklistedToken blacklistedToken = new BlacklistedToken();
        Date currentDate = new Date();
        blacklistedToken.setToken(token);
        blacklistedToken.setExpirationDate(new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION));
        blackListService.save(blacklistedToken);
    }






}
