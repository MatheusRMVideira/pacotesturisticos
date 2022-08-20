package br.ufscar.dc.dsw.pacotesturisticos.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.ufscar.dc.dsw.pacotesturisticos.dao.IClienteDAO;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Cliente;
import br.ufscar.dc.dsw.pacotesturisticos.service.spec.IClienteService;

@Service
@Transactional(readOnly = false)
public class ClienteService implements IClienteService{
    
    @Autowired
    IClienteDAO clienteDAO;

    public void save(Cliente cliente) {
        clienteDAO.save(cliente);
    }

    public void deleteById(long id) {
        clienteDAO.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Cliente findById(long id) {
        return clienteDAO.findById(id);
    }

    @Transactional(readOnly = true)
    public Cliente findByCpf(String cpf) {
        return clienteDAO.findByCpf(cpf);
    }

    @Transactional(readOnly = true)
    public Cliente findByEmail(String email) {
        return clienteDAO.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return clienteDAO.findAll();
    }

    @Transactional(readOnly = true)
    public boolean clienteTemCompras(long id) {
        return !clienteDAO.findById(id).getCompras().isEmpty();
    }
    
}
