package org.repaso.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;

@Data

@AllArgsConstructor

public class Pedido {
    private int idPedido;
    private double total;
    private LocalDate fecha;
    private int idCliente;
    private int idComercial;
}
