package com.idos.apk.backend.tienda.tatuajes.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
@Table(name = "ordenes")
@Builder
public class Orden {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(length = 36, nullable = false, updatable = false)
    private String id;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    private String agno;

    private int mes;

    @Column(nullable = false, length = 20)
    private Double total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "orden", orphanRemoval = true)
    private List<DetalleOrden> detalle = new ArrayList<>();


    @PrePersist
    public void prePersist() {
        fechaCreacion = LocalDate.now();
         mes = Integer.parseInt(fechaCreacion.format(DateTimeFormatter.ofPattern("MM")));
         agno = fechaCreacion.format(DateTimeFormatter.ofPattern("yyyy"));
    }

}
