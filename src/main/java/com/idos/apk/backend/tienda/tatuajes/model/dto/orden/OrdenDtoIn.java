package com.idos.apk.backend.tienda.tatuajes.model.dto.orden;

import com.idos.apk.backend.tienda.tatuajes.model.dto.detalleorden.DetalleOrdenDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrdenDtoIn(@NotNull(message = "No puede ser nulo")
                         @NotEmpty(message = "No puede ser vacio")
                         List<DetalleOrdenDto> lista,
                         @NotNull(message = "No puede ser nulo")
                         @NotEmpty(message = "No puede ser vacio")
                         String token,
                         @NotNull(message = "No puede ser nulo")
                         double total) {
}
