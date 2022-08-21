package br.ufscar.dc.dsw.pacotesturisticos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.ufscar.dc.dsw.pacotesturisticos.dao.IClienteDAO;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Cliente;
import br.ufscar.dc.dsw.pacotesturisticos.dao.IAgenciaDAO;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Agencia;

public class UsuarioDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IClienteDAO clienteDAO;

    @Autowired
    private IAgenciaDAO agenciaDAO;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente cliente = clienteDAO.findByEmail(email);
        Agencia agencia = agenciaDAO.findByEmail(email);
        if (cliente == null && agencia == null) {
            throw new UsernameNotFoundException("Usuário ou Agência não encontrado");
        }
        return new UsuarioDetails(cliente, agencia);
    }
    
}
