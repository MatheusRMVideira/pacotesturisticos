package br.ufscar.dc.dsw.pacotesturisticos.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.ufscar.dc.dsw.pacotesturisticos.dao.ICompraDAO;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Compra;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Cliente;
import br.ufscar.dc.dsw.pacotesturisticos.service.spec.ICompraService;

@Service
@Transactional(readOnly = false)
public class CompraService implements ICompraService{
    
    @Autowired
    ICompraDAO compraDAO;

    public void save(Compra compra) {
        compraDAO.save(compra);
    }

    
    public void deleteById(long id) {
        compraDAO.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Compra findById(long id) {
        return compraDAO.findById(id);
    }

    @Transactional(readOnly = true)
    public Compra findByCliente(Cliente cliente) {
        return compraDAO.findByCliente(cliente);
    }


    @Transactional(readOnly = true)
    public List<Compra> findAll() {
        return compraDAO.findAll();
    }

}
