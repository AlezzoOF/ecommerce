package com.idos.apk.backend.tienda.tatuajes.dto.user;

import jakarta.validation.constraints.Email;
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
public class LoginDto {
    @NotNull(message = "No puede ser nulo")
    @NotEmpty(message = "No puede ser vacio")
    @Size(max = 100, message = "Exedio el tamano max")
    @Email(message = "Email no valido")
    private String userName;

    @NotNull(message = "No puede ser nulo")
    @NotEmpty(message = "No puede ser vacio")
    @Size(max = 100, message = "Exedio el tamano max")
    private String pwd;
}
