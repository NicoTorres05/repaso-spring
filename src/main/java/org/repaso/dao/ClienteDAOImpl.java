package org.repaso.dao;


import lombok.extern.slf4j.Slf4j;
import org.repaso.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Slf4j

@Repository
public class ClienteDAOImpl implements ClienteDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Cliente cliente) {

        String sqlInsert = """
							INSERT INTO cliente (nombre, apellido1, apellido2, ciudad, categoría, email) 
							VALUES  (     ?,         ?,         ?,       ?,         ?,         ?)
						   """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rows = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlInsert, new String[] { "id" });
            int idx = 1;
            ps.setString(idx++, cliente.getNombre());
            ps.setString(idx++, cliente.getApellido1());
            ps.setString(idx++, cliente.getApellido2());
            ps.setString(idx++, cliente.getCiudad());
            ps.setInt(idx++, cliente.getCategoria());
            ps.setString(idx, cliente.getEmail());
            return ps;
        }, keyHolder);

        cliente.setId(keyHolder.getKey().intValue());

        log.info("Insertados {} registros.", rows);
    }

    @Override
    public List<Cliente> getAll() {
        List<Cliente> listCliente = jdbcTemplate.query(
                "SELECT * FROM cliente",
                (rs, rowNum) -> new Cliente(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido1"),
                        rs.getString("apellido2"),
                        rs.getString("ciudad"),
                        rs.getInt("categoría"),
                        rs.getString("email")
                )
        );

        log.info("Devueltos {} registros.", listCliente.size());

        return listCliente;
    }

    @Override
    public Optional<Cliente> find(int id) {
        Cliente fab =  jdbcTemplate
                .queryForObject("SELECT * FROM cliente WHERE id = ?"
                        , (rs, rowNum) -> new Cliente(rs.getInt("id"),
                                rs.getString("nombre"),
                                rs.getString("apellido1"),
                                rs.getString("apellido2"),
                                rs.getString("ciudad"),
                                rs.getInt("categoría"),
                                rs.getString("email"))
                        , id
                );

        if (fab != null) {
            return Optional.of(fab);}
        else {
            log.info("Cliente no encontrado.");
            return Optional.empty(); }
    }

    @Override
    public void update(Cliente cliente) {
        int rows = jdbcTemplate.update("""
										UPDATE cliente SET 
														nombre = ?, 
														apellido1 = ?, 
														apellido2 = ?,
														ciudad = ?,
														categoría = ?,  
                                                        email = ?
												WHERE id = ?
										""", cliente.getNombre()
                , cliente.getApellido1()
                , cliente.getApellido2()
                , cliente.getCiudad()
                , cliente.getCategoria()
                , cliente.getEmail()
                , cliente.getId());

        log.info("Update de Cliente con {} registros actualizados.", rows);
    }

    @Override
    public void delete(long id) {
        int rows = jdbcTemplate.update("DELETE FROM cliente WHERE id = ?", id);

        log.info("Delete de Cliente con {} registros eliminados.", rows);
    }
}
