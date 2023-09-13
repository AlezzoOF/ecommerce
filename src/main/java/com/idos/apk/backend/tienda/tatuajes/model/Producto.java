package com.idos.apk.backend.tienda.tatuajes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "productos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Producto {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(length = 36, nullable = false, updatable = false)
    private String id;

    @Column(unique = true, nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 200)
    private String descripcion;

    private String img;

    @Column(nullable = false, length = 100)
    private Double precio;

    @Column(nullable = false, length = 100)
    @Min(0)
    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "tipoProductoId")
    private TipoProducto tipo;
    @Builder.Default
    private boolean enable = true;

}
