package br.ufscar.dc.dsw.pacotesturisticos.service.spec;

import java.util.List;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Cliente;

public interface IClienteService {
    Cliente findById(long id);
    Cliente findByCpf(String cpf);
    Cliente findByEmail(String email);
    List<Cliente> findAll();
    void save(Cliente cliente);
    void deleteById(long id);
    boolean clienteTemCompras(long id);
}
