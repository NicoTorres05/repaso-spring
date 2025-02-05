package org.repaso.dao;

import org.repaso.model.Pedido;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoDAO {
    public List<Pedido> getAll();
    public List<Pedido> getByComercial(int idComercial);
    public List<Pedido> getByCliente(int idCliente);
    public void delete(int id);
    public int getTotalPedidos();
}
