package br.ufscar.dc.dsw.pacotesturisticos.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.pacotesturisticos.dao.IClienteDAO;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Cliente;

@Component
public class UniqueCpfValidator implements ConstraintValidator<UniqueCpf, String>{
    
    @Autowired
    private IClienteDAO clienteDAO;

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {
        if (clienteDAO != null) {
            Cliente cliente = clienteDAO.findByCpf(cpf);
            return cliente == null;
        } else {
            return true;
        }
    }
}
