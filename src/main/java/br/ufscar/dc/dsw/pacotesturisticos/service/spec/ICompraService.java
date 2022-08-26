package br.ufscar.dc.dsw.pacotesturisticos.service.spec;

import java.util.List;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Compra;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Cliente;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Pacote;

public interface ICompraService {
    Compra findById(long id);
    List<Compra> findByCliente(Cliente cliente);
    List<Compra> findByPacote(Pacote pacote);
    List<Compra> findAll();
    void save(Compra compra);
    void deleteById(long id);
}
