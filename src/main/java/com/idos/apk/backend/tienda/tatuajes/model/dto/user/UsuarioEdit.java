package com.idos.apk.backend.tienda.tatuajes.model.dto.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioEdit(
                          @NotNull(message = "No puede ser nulo")
                          @NotEmpty(message = "No puede ser vacio")
                          @Size(max = 100, message = "Exedio el tamano max")
                          String nombre,
                          @NotNull(message = "No puede ser nulo")
                          @NotEmpty(message = "No puede ser vacio")
                          @Size(max = 100, message = "Exedio el tamano max")
                          String apellido,
                          @NotNull(message = "No puede ser nulo")
                          @NotEmpty(message = "No puede ser vacio")
                          @Size(max = 100, message = "Exedio el tamano max")
                          String direccion,
                          @NotNull(message = "No puede ser nulo")
                          @NotEmpty(message = "No puede ser vacio")
                          @Size(max = 100, message = "Exedio el tamano max")
                          String rol) {
}
