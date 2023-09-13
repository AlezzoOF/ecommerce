package com.idos.apk.backend.tienda.tatuajes.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tipos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoProducto {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(length = 36, nullable = false, updatable = false)
    private String id;
    @Column(unique = true, nullable = false, length = 100)
    private String name;

    public TipoProducto(String name) {
        this.name = name;
    }
}
