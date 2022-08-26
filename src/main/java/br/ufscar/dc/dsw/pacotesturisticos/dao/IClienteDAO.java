package br.ufscar.dc.dsw.pacotesturisticos.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Cliente;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

@SuppressWarnings("unchecked")
public interface IClienteDAO extends CrudRepository<Cliente, Long>{
    Cliente findById(long id);
    List<Cliente> findAll();
    Cliente save(Cliente cliente);
    void deleteById(long id);

    @Query("SELECT c FROM Cliente c WHERE c.email = :email")
    public Cliente findByEmail(@Param("email") String email);
    @Query("SELECT c FROM Cliente c WHERE c.cpf = :cpf")
    public Cliente findByCpf(@Param("cpf") String cpf);
}
