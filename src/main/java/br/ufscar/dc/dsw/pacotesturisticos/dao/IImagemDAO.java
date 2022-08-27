package br.ufscar.dc.dsw.pacotesturisticos.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Imagem;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Pacote;

@SuppressWarnings("unchecked")
public interface IImagemDAO extends CrudRepository<Imagem, Long>{
    Imagem findById(long id);
   // List<Imagem> findByPacote(Long pacoteTuristico);
    List<Imagem> findByPacote(Pacote pacote);
    List<Imagem> findAll();
    Imagem save(Imagem imagem);
    void deleteById(long id);
}
