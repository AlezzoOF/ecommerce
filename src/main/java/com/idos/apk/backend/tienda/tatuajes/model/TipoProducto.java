package com.idos.apk.backend.tienda.tatuajes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tipos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoProducto {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

}
