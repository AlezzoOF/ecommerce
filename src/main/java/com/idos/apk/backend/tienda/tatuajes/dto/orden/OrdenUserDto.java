package com.idos.apk.backend.tienda.tatuajes.dto.orden;

import com.idos.apk.backend.tienda.tatuajes.dto.detalle.DetalleOrdenOutDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdenUserDto {
    private Double total;
    private String numero;
    private LocalDate fechaCreacion;
    private List<DetalleOrdenOutDto> detalles;
}
