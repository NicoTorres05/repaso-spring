package org.repaso.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PedidoDTO {
    private int id;
    private double total;
    private LocalDate fecha;
    private int idCliente;
    private int idComercial;

    private String nombreCliente;
    private String nombreComercial;
}
