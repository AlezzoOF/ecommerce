package com.idos.apk.backend.tienda.tatuajes.dto.detalle;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleOrdenInDto {

    @NotNull(message = "No puede ser nulo")
    private Integer cantidad;
    @NotNull(message = "No puede ser nulo")
    @NotEmpty(message = "No puede ser vacio")
    private String id_producto;
    @NotNull(message = "No puede ser nulo")
    private Double total;
}
