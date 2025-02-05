package org.repaso.dao;

import java.util.List;
import java.util.Optional;

import org.repaso.model.Comercial;
import org.springframework.stereotype.Repository;

@Repository
public interface ComercialDAO {
    public void create(Comercial comercial);
    public List<Comercial> getAll();
    public Optional<Comercial> find(int id);
    public void update(Comercial comercial);
    public void delete(int id);

}
