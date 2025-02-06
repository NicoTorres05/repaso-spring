package org.repaso.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.repaso.validation.RangoCategoria;
import org.repaso.validation.RangoCategoriaPlus;

@Data

@AllArgsConstructor

public class Cliente {

    private int id;

    //El @size se puede poner junto el max y el min o separado
    @NotBlank(message = "{err.n.nom}")
    @Size(max = 30, message = "{err.s.nom}")
    private String nombre;

    @NotBlank(message = "{err.n.ln}")
    @Size(max = 30, message = "{err.s.ln}")
    private String apellido1;

    private String apellido2;

    @NotBlank(message = "{err.n.c}")
    @Size(max = 50, message = "{err.s.c}")
    private String ciudad;

    @RangoCategoriaPlus({100, 200, 300, 400, 500, 600, 700, 800, 900, 1000})
    private int categoria;

    @Email(message = "{err.f.email}", regexp="^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,5}")
    private String email;

    public Cliente() {}
}
