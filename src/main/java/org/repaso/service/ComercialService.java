package org.repaso.service;

import org.repaso.dao.ClienteDAO;
import org.repaso.dao.ComercialDAO;
import org.repaso.dao.PedidoDAO;
import org.repaso.dto.PedidoDTO;
import org.repaso.mapstruct.PedidoMapper;
import org.repaso.model.Cliente;
import org.repaso.model.Comercial;
import org.repaso.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ComercialService {
    private ComercialDAO comercialDAO;
    private PedidoDAO pedidoDAO;
    private ClienteDAO clienteDAO;

    @Autowired
    private PedidoMapper pedidoMapper;

    public ComercialService(ComercialDAO comercialDAO, PedidoDAO pedidoDAO, ClienteDAO clienteDAO) {
        this.comercialDAO = comercialDAO;
        this.pedidoDAO = pedidoDAO;
        this.clienteDAO = clienteDAO;
    }

    public List<Comercial> listAll() {

        return comercialDAO.getAll();

    }

    public Comercial one(Integer id) {
        Optional<Comercial> optCom = comercialDAO.find(id);
        if (optCom.isPresent())
            return optCom.get();
        else
            return null;
    }

    public void newComercial(Comercial comercial) {

        comercialDAO.create(comercial);

    }

    public void updateComercial(Comercial comercial) {

        comercialDAO.update(comercial);

    }

    public void deleteComercial(int id) {
        // ¿Está seguro de que quiere eliminar al comercial? Se borrarán también todos sus pedidos

        // Sí
        List<Pedido> pedidos = pedidoDAO.getByComercial(id);
        pedidos.forEach(p -> pedidoDAO.delete(p.getIdPedido()));
        comercialDAO.delete(id);

        // No
        // Borrado cancelado
    }

    public List<PedidoDTO> listPedidosDTO(int idComercial) {

        List<Cliente> clientes = clienteDAO.getAll();
        List<Pedido> pedidos = pedidoDAO.getByComercial(idComercial);
        pedidos.sort((a, b) -> b.getFecha().compareTo(a.getFecha()));

        List<PedidoDTO> pedidosDTO = new ArrayList<>();



        pedidos.forEach(p -> {
            int idC = p.getIdCliente();
            String nombre = clientes.stream()
                    .filter(c -> c.getId() == idC)
                    .map(c -> c.getNombre() + " " + c.getApellido1() + " " + (c.getApellido2() != null ? c.getApellido2() : ""))
                    .findFirst().orElse("");

            pedidosDTO.add(pedidoMapper.pedidoAPedidoDTO(p, nombre, ""));
        });

        return pedidosDTO;
    }
    public int getTotalPedidos() {
        return pedidoDAO.getTotalPedidos();
    }

    public double getPorcentajePedidos(int idComercial) {
        return (double)listPedidosDTO(idComercial).size() / getTotalPedidos() * 100;
    }
}
