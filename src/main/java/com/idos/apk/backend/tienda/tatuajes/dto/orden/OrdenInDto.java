package com.idos.apk.backend.tienda.tatuajes.dto.orden;

import com.idos.apk.backend.tienda.tatuajes.dto.detalle.DetalleOrdenInDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdenInDto {
    @NotNull(message = "No puede ser nulo")
    @NotEmpty(message = "No puede ser vacio")
    private List<DetalleOrdenInDto> lista;

    @NotNull(message = "No puede ser nulo")
    @NotEmpty(message = "No puede ser vacio")
    private String token;

    @NotNull(message = "No puede ser nulo")
    private Double total;
}
