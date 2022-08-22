package br.ufscar.dc.dsw.pacotesturisticos.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Agencia;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

@SuppressWarnings("unchecked")
public interface IAgenciaDAO extends CrudRepository<Agencia, Long>{
    Agencia findById(long id);
    List<Agencia> findAll();
    Agencia save(Agencia agencia);
    void deleteById(long id);

    @Query("SELECT a FROM Agencia a WHERE a.email = :email")
    public Agencia findByEmail(@Param("email") String email);
    @Query("SELECT a FROM Agencia a WHERE a.cnpj = :cnpj")
    public Agencia findByCnpj(@Param("cnpj") String cnpj);
}
