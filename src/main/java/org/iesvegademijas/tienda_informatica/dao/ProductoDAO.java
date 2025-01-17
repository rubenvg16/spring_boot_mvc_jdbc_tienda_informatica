package org.iesvegademijas.tienda_informatica.dao;

import org.iesvegademijas.tienda_informatica.modelo.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidioDAO {

    public void create(Pedido pedido);

    public List<Pedido> getAll();
    public Optional<Pedido> find(int id);

    public void update(Pedido pedido);

    public void delete(int id);

}
