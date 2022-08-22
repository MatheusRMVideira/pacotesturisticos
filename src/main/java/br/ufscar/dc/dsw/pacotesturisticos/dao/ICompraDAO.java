package br.ufscar.dc.dsw.pacotesturisticos.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Compra;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Cliente;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Pacote;

@SuppressWarnings("unchecked")
public interface ICompraDAO extends CrudRepository<Compra, Long>{
    Compra findById(long id);
    List<Compra> findByCliente(Cliente cliente);
    List<Compra> findByPacote(Pacote pacote);
    List<Compra> findAll();
    Compra save(Compra compra);
    void deleteById(long id);
}
