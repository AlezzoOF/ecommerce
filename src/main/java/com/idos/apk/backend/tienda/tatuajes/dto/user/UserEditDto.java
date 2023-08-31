package com.idos.apk.backend.tienda.tatuajes.dto.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEditDto {
    @NotNull(message = "No puede ser nulo")
    @NotEmpty(message = "No puede ser vacio")
    @Size(max = 100, message = "Exedio el tamano max")
    private String nombre;

    @NotNull(message = "No puede ser nulo")
    @NotEmpty(message = "No puede ser vacio")
    @Size(max = 100, message = "Exedio el tamano max")
    private String apellido;

    @NotNull(message = "No puede ser nulo")
    @NotEmpty(message = "No puede ser vacio")
    @Size(max = 100, message = "Exedio el tamano max")
    private String direccion;

    @NotNull(message = "No puede ser nulo")
    @NotEmpty(message = "No puede ser vacio")
    @Size(max = 100, message = "Exedio el tamano max")
    private String rol;
}
