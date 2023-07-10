package com.idos.apk.backend.tienda.tatuajes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(length = 36, nullable = false, updatable = false)
    private String id;
    @Column(nullable = false, length = 100)
    private String nombre;
    @Column(nullable = false, length = 100)
    private String apellido;
    @Column(nullable = false, length = 200)
    private String direccion;
    @Column(unique = true, nullable = false, length = 255)
    private String email;
    @Column(nullable = false, length = 100)
    private String pwd;

    private boolean enable;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Orden> ordenes = new ArrayList<>();


    private String rol;


}
