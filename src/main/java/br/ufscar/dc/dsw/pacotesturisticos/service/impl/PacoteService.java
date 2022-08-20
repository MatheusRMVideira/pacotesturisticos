package br.ufscar.dc.dsw.pacotesturisticos.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.ufscar.dc.dsw.pacotesturisticos.dao.IPacoteDAO;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Pacote;
import br.ufscar.dc.dsw.pacotesturisticos.service.spec.IPacoteService;

@Service
@Transactional(readOnly = false)
public class PacoteService implements IPacoteService{
    
    @Autowired
    IPacoteDAO pacoteDAO;

    public void save(Pacote pacote) {
        pacoteDAO.save(pacote);
    }

    public void deleteById(long id) {
        pacoteDAO.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Pacote findById(long id) {
        return pacoteDAO.findById(id);
    }

    @Transactional(readOnly = true)
    public Pacote findByAgencia(long agencia) {
        return pacoteDAO.findByAgencia(agencia);
    }

    @Transactional(readOnly = true)
    public List<Pacote> findAll() {
        return pacoteDAO.findAll();
    }

    @Transactional(readOnly = true)
    public boolean pacoteTemImagem(long id) {
        return !pacoteDAO.findById(id).getImagens().isEmpty();
    }
}
