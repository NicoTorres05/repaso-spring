package org.repaso.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data

@AllArgsConstructor

public class Comercial {

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

    private float comision;

    public Comercial() {}
}
