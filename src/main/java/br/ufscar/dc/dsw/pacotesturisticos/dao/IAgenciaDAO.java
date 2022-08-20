package br.ufscar.dc.dsw.pacotesturisticos.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Agencia;

@SuppressWarnings("unchecked")
public interface IAgenciaDAO extends CrudRepository<Agencia, Long>{
    Agencia findById(long id);
    Agencia findByCnpj(String cnpj);
    Agencia findByEmail(String email);
    List<Agencia> findAll();
    Agencia save(Agencia agencia);
    void deleteById(long id);
}
