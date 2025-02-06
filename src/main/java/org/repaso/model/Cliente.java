package org.repaso.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data

@AllArgsConstructor

public class Cliente {

    private int id;

    //El @size se puede poner junto el max y el min o separado
    @NotBlank(message = "Por favor, introduce un nombre.")
    @Size(min = 4, max = 20, message = "La ciudad debe contener entre 4 y 20 caracteres.")
    private String nombre;

    @NotBlank(message = "Por favor, introduce un apellido.")
    @Size(min = 4, max = 20, message = "La ciudad debe contener entre 4 y 20 caracteres.")
    private String apellido1;

    @Size(min = 4, max = 20, message = "La ciudad debe contener entre 4 y 20 caracteres.")
    private String apellido2;

    @NotBlank(message = "Por favor, introduce una ciudad.")
    @Size(min = 4, max = 20, message = "La ciudad debe contener entre 4 y 20 caracteres.")
    private String ciudad;

    private int categoria;

    public Cliente() {}
}
