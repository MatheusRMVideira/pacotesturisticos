package br.ufscar.dc.dsw.pacotesturisticos.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Pacote;

@SuppressWarnings("unchecked")
public interface IPacoteDAO extends CrudRepository<Pacote, Long>{
    Pacote findById(long id);
    Pacote findByAgencia(long agencia);
    List<Pacote> findAll();
    Pacote save(Pacote pacote);
    void deleteById(long id);
}
