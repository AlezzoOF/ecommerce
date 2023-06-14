package com.idos.apk.backend.tienda.tatuajes.model.dto.detalleorden;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record DetalleOrdenDto(@NotNull(message = "No puede ser nulo")
                              int cantidad,
                              @NotNull(message = "No puede ser nulo")
                              @NotEmpty(message = "No puede ser vacio")
                              String id,
                              @NotNull(message = "No puede ser nulo")
                              double total) {
}
