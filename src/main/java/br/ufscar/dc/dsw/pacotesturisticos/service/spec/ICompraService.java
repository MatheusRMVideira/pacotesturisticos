package br.ufscar.dc.dsw.pacotesturisticos.service.spec;

import java.util.List;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Compra;

public interface ICompraService {
    Compra findById(long id);
    Compra findByCliente(long cliente);
    List<Compra> findAll();
    void save(Compra compra);
    void deleteById(long id);
}
