package com.idos.apk.backend.tienda.tatuajes.security;

import com.idos.apk.backend.tienda.tatuajes.model.Usuario;
import com.idos.apk.backend.tienda.tatuajes.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UsuarioRepository repository;

//Metodo para cargar el usuario de la DB y combertirlo en un UserDetails
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new User(user.getEmail(), user.getPwd(), mapRolToAuthorities(user.getRol()));
    }
//Genera una colleccion de las authorities del usuario cargado
    private Collection<GrantedAuthority> mapRolToAuthorities(String rol) {
//        return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getName())).collect(Collectors.toList());
        Collection<GrantedAuthority> enviar = new ArrayList<>();
        enviar.add(new SimpleGrantedAuthority(rol));
        return enviar;

    }
}
