package com.idos.apk.backend.tienda.tatuajes.model.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LoginDto(@NotNull(message = "No puede ser nulo")
                       @NotEmpty(message = "No puede ser vacio")
                       @Size(max = 100, message = "Exedio el tamano max")
                       @Email(message = "Email no valido")
                       String userName,
                       @NotNull(message = "No puede ser nulo")
                       @NotEmpty(message = "No puede ser vacio")
                       @Size(max = 100, message = "Exedio el tamano max")
                       String pwd) {
}
