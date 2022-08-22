package br.ufscar.dc.dsw.pacotesturisticos.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.pacotesturisticos.dao.IAgenciaDAO;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Agencia;
import br.ufscar.dc.dsw.pacotesturisticos.dao.IClienteDAO;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Cliente;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String>{
    
    @Autowired
    private IAgenciaDAO agenciaDAO;

    @Autowired
    private IClienteDAO clienteDAO;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (agenciaDAO != null) {
            Agencia agencia = agenciaDAO.findByEmail(email);

            return agencia == null;
        }
        if (clienteDAO != null) {
            Cliente cliente = clienteDAO.findByEmail(email);
            return cliente == null;
        } else {
            return true;
        }
    }
}
