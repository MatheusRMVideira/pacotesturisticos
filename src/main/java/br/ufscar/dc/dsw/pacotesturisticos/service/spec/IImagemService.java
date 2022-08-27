package br.ufscar.dc.dsw.pacotesturisticos.service.spec;

import java.util.List;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Imagem;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Pacote;

public interface IImagemService {
    Imagem findById(long id);
    List<Imagem> findByPacote(Pacote pacote);
    //List<Imagem> findByPacote(Long pacoteTuristico);
    List<Imagem> findAll();
    void save(Imagem imagem);
    void deleteById(long id);
}
