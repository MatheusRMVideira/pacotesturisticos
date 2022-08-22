package br.ufscar.dc.dsw.pacotesturisticos.service.spec;

import java.util.List;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Pacote;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Agencia;


public interface IPacoteService {
    Pacote findById(long id);
    Pacote findByAgencia(Agencia agencia);
    List<Pacote> findAll();
    void save(Pacote pacote);
    void deleteById(long id);
    boolean pacoteTemImagem(long id);
}
