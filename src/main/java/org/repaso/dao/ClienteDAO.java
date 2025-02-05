package org.repaso.dao;

import org.repaso.model.Cliente;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteDAO {
public void create(Cliente cliente);
public List<Cliente> getAll();
public Optional<Cliente> find(int id);
public void update(Cliente cliente);
public void delete(long id);
}
