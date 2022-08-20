package br.ufscar.dc.dsw.pacotesturisticos.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Compra;

@SuppressWarnings("unchecked")
public interface ICompraDAO extends CrudRepository<Compra, Long>{
    Compra findById(long id);
    Compra findByCliente(Long cliente);
    List<Compra> findAll();
    Compra save(Compra compra);
    void deleteById(long id);
}
