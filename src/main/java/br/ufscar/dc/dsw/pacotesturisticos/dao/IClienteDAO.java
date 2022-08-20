package br.ufscar.dc.dsw.pacotesturisticos.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Cliente;

@SuppressWarnings("unchecked")
public interface IClienteDAO extends CrudRepository<Cliente, Long>{
    Cliente findById(long id);
    Cliente findByCpf(String cpf);
    Cliente findByEmail(String email);
    List<Cliente> findAll();
    Cliente save(Cliente cliente);
    void deleteById(long id);
}
