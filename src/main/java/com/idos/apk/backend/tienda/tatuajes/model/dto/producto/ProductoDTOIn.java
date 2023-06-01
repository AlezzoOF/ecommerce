package com.idos.apk.backend.tienda.tatuajes.model.dto.producto;

import jakarta.validation.constraints.*;


public record ProductoDTOIn(
                            @NotNull(message = "No puede ser nulo")
                            @NotEmpty(message = "No puede ser vacio")
                            @Size(max = 100, message = "Exedio el tamano max")
                            String nombre,
                            @NotNull(message = "No puede ser nulo")
                            @NotEmpty(message = "No puede ser vacio")
                            @Size(max = 200, message = "Exedio el tamano max")
                            String descripcion,
                            @NotNull(message = "No puede ser nulo")
                            @NotEmpty(message = "No puede ser vacio")
                            @Size(max = 50, message = "Exedio el tamano max")
                            @Min(0)
                            double precio,
                            @NotNull(message = "No puede ser nulo")
                            @NotEmpty(message = "No puede ser vacio")
                            @Min(0)
                            @Size(max = 50, message = "Exedio el tamano max")
                            int cantidad,
                            @NotNull(message = "No puede ser nulo")
                            @NotEmpty(message = "No puede ser vacio")
                            @Size(max = 50, message = "Exedio el tamano max")
                            String tipo) {
}
