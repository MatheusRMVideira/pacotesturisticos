package br.ufscar.dc.dsw.pacotesturisticos.service.spec;

import java.util.List;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Agencia;

public interface IAgenciaService {
    Agencia findById(long id);
    Agencia findByCnpj(String cnpj);
    Agencia findByEmail(String email);
    List<Agencia> findAll();
    void save(Agencia agencia);
    void deleteById(long id);
    boolean agenciaTemPacotes(long id);
}
