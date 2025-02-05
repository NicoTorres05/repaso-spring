package org.repaso.service;

import org.repaso.dao.ClienteDAO;
import org.repaso.dao.ComercialDAO;
import org.repaso.dao.PedidoDAO;
import org.repaso.dto.PedidoDTO;
import org.repaso.model.Cliente;
import org.repaso.model.Comercial;
import org.repaso.mapstruct.PedidoMapper;
import org.repaso.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private ClienteDAO clienteDAO;
    @Autowired
    private PedidoMapper pedidoMapper;

    @Autowired
    private PedidoDAO pedidoDAO;

    @Autowired
    private ComercialDAO comercialDAO;

    public ClienteService(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public List<Cliente> listAll() {
        return clienteDAO.getAll();
    }

    public Cliente one(Integer id) {
        Optional<Cliente> optCli =  clienteDAO.find(id);
        if (optCli.isPresent()) {
            return optCli.get();
        } else {
            return null;
        }
    }

    public void newCliente(Cliente cliente) {
        clienteDAO.create(cliente);
    }

    public void updateCliente(Cliente cliente) {

        clienteDAO.update(cliente);

    }

    public void deleteCliente(Integer id) {
        List<Pedido> pedidos = pedidoDAO.getByCliente(id);
        pedidos.forEach(p -> pedidoDAO.delete(p.getIdCliente()));
        clienteDAO.delete(id);
    }

    public List<PedidoDTO> listPedidosDTO(int idCliente) {
        List<Comercial> comerciales = comercialDAO.getAll();
        List<Pedido> pedidos = pedidoDAO.getByCliente(idCliente);
        pedidos.sort((a, b) -> b.getFecha().compareTo(a.getFecha()));

        List<PedidoDTO> pedidosDTO = new ArrayList<>();

        pedidos.forEach(p -> {
            int idC = p.getIdCliente();
            String nombre = comerciales.stream()
                    .filter(c -> c.getId() == idC)
                    .map(c -> c.getNombre() + " " + c.getApellido1() + " " + (c.getApellido2() != null ? c.getApellido2() : ""))
                    .findFirst().orElse("");

            pedidosDTO.add(pedidoMapper.pedidoAPedidoDTO(p, "", nombre));
        });
        return pedidosDTO;
    }
}
