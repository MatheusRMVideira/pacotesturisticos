package br.ufscar.dc.dsw.pacotesturisticos.conversor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.pacotesturisticos.domain.Agencia;
import br.ufscar.dc.dsw.pacotesturisticos.service.spec.IAgenciaService;

@Component
public class AgenciaConversor implements Converter<String, Agencia> {
    
    @Autowired
    private IAgenciaService agenciaService;
    
    @Override
    public Agencia convert(String id) {
        return agenciaService.findById(Long.parseLong(id));
    }
}