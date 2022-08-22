package br.ufscar.dc.dsw.pacotesturisticos.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Compra;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Cliente;

@SuppressWarnings("unchecked")
public interface ICompraDAO extends CrudRepository<Compra, Long>{
    Compra findById(long id);
    Compra findByCliente(Cliente cliente);
    List<Compra> findAll();
    Compra save(Compra compra);
    void deleteById(long id);
}
