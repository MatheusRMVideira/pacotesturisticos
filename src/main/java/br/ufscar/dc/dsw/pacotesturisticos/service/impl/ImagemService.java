package br.ufscar.dc.dsw.pacotesturisticos.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.ufscar.dc.dsw.pacotesturisticos.dao.IImagemDAO;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Imagem;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Pacote;
import br.ufscar.dc.dsw.pacotesturisticos.service.spec.IImagemService;

@Service
@Transactional(readOnly = false)
public class ImagemService implements IImagemService{
    
    @Autowired
    IImagemDAO imagemDAO;

    public void save(Imagem imagem) {
        imagemDAO.save(imagem);
    }

    public void deleteById(long id) {
        imagemDAO.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Imagem findById(long id) {
        return imagemDAO.findById(id);
    }

    @Transactional(readOnly = true)
	/*
	 * public List<Imagem> findByPacote(Long pacoteTuristico) { return
	 * imagemDAO.findByPacote(pacoteTuristico); }
	 */
    public List<Imagem> findByPacote(Pacote pacote) { 
    	return imagemDAO.findByPacote(pacote); 
    }

    @Transactional(readOnly = true)
    public List<Imagem> findAll() {
        return imagemDAO.findAll();
    }
}
