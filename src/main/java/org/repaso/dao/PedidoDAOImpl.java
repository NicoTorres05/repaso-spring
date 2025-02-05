package org.repaso.dao;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.repaso.model.Pedido;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@AllArgsConstructor
public class PedidoDAOImpl implements PedidoDAO {

    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Pedido> getAll() {
        List<Pedido> listPedido = jdbcTemplate.query(
                "SELECT * FROM pedido",
                (rs, rowNum) -> new Pedido(rs.getInt("id"),
                        rs.getDouble("total"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getInt("id_cliente"),
                        rs.getInt("id_comercial"))
        );

        log.info("Devueltos {} registros.", listPedido.size());

        return listPedido;
    }

    @Override
    public List<Pedido> getByComercial(int idComercial) {
        List<Pedido> listPedido = jdbcTemplate.query(
                "SELECT * FROM pedido WHERE id_comercial = ?",
                (rs, rowNum) -> new Pedido(rs.getInt("id"),
                        rs.getDouble("total"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getInt("id_cliente"),
                        rs.getInt("id_comercial"))
                , idComercial
        );

        log.info("Devueltos {} pedidos del comercial con id {}.", listPedido.size(), idComercial);

        return listPedido;
    }

    @Override
    public List<Pedido> getByCliente(int idCliente) {
        List<Pedido> listPedido = jdbcTemplate.query(
                "SELECT * FROM pedido WHERE id_cliente = ?",
                (rs, rowNum) -> new Pedido(rs.getInt("id"),
                        rs.getDouble("total"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getInt("id_cliente"),
                        rs.getInt("id_comercial"))
                , idCliente
        );

        log.info("Devueltos {} pedidos del cliente con id {}.", listPedido.size(), idCliente);

        return listPedido;
    }

    @Override
    public void delete(int id) {
        int rows = jdbcTemplate.update("DELETE FROM pedido WHERE id = ?", id);

        log.info("Delete de Pedido con {} registros eliminados.", rows);
    }

    @Override
    public int getTotalPedidos() {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM pedido", Integer.class);
    }
}
