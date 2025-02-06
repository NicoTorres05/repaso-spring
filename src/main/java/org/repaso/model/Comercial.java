package org.repaso.model;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data

@AllArgsConstructor

public class Comercial {

    private int id;

    //El @size se puede poner junto el max y el min o separado
    @NotBlank(message = "{err.n.nom}")
    @Size(max = 30, message = "{err.s.nom}")
    private String nombre;

    @NotBlank(message = "{err.n.ln}")
    @Size(max = 30, message = "{err.n.ln}")
    private String apellido1;

    private String apellido2;

    @DecimalMin(value = "0.276", inclusive = true, message = "{err.min.com}")
    @DecimalMax(value = "0.946", inclusive = true, message = "{err.max.dom}")
    private BigDecimal comision;

    public Comercial() {}
}
