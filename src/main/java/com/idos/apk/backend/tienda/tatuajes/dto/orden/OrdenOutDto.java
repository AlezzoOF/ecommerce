package com.idos.apk.backend.tienda.tatuajes.dto.orden;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdenOutDto {
    private Double total;
    private String numero;
    private LocalDate fechaCreacion;
    private String id_creador;
}
