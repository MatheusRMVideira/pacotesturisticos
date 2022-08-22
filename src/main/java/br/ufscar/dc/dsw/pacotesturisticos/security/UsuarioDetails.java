package br.ufscar.dc.dsw.pacotesturisticos.security;

import java.util.Arrays;
import java.util.Collection;
 
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.ufscar.dc.dsw.pacotesturisticos.domain.Cliente;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Agencia;

@SuppressWarnings("serial")
public class UsuarioDetails implements UserDetails {
    
    private Cliente cliente;

    private Agencia agencia;

    public UsuarioDetails(Cliente cliente, Agencia agencia) {
        this.cliente = cliente;
        this.agencia = agencia;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String tipo = "";
        if (agencia != null) {
            tipo = "ROLE_AGENCIA";
        } else {
            tipo = cliente.getTipo();
        }
        return Arrays.asList(new SimpleGrantedAuthority(tipo));
    }

    @Override
    public String getPassword() {
        if(cliente != null) {
            return cliente.getSenha();
        } else {
            return agencia.getSenha();
        }
    }

    @Override
    public String getUsername() {
        if(cliente != null) {
            return cliente.getEmail();
        } else {
            return agencia.getEmail();
        }
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Agencia getAgencia() {
        return agencia;
    }
    
}