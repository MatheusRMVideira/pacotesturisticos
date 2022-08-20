package br.ufscar.dc.dsw.pacotesturisticos.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.ufscar.dc.dsw.pacotesturisticos.dao.IAgenciaDAO;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Agencia;
import br.ufscar.dc.dsw.pacotesturisticos.service.spec.IAgenciaService;

@Service
@Transactional(readOnly = false)
public class AgenciaService implements IAgenciaService{
    @Autowired
    IAgenciaDAO agenciaDAO;

    public void save(Agencia agencia) {
        agenciaDAO.save(agencia);
    }

    public void deleteById(long id) {
        agenciaDAO.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Agencia findById(long id) {
        return agenciaDAO.findById(id);
    }

    @Transactional(readOnly = true)
    public Agencia findByCnpj(String cnpj) {
        return agenciaDAO.findByCnpj(cnpj);
    }

    @Transactional(readOnly = true)
    public Agencia findByEmail(String email) {
        return agenciaDAO.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public List<Agencia> findAll() {
        return agenciaDAO.findAll();
    }

    @Transactional(readOnly = true)
    public boolean agenciaTemPacotes(long id) {
        return !agenciaDAO.findById(id).getPacotes().isEmpty();
    }

}
