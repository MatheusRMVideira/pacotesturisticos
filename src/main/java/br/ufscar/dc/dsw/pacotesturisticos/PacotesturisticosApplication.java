package br.ufscar.dc.dsw.pacotesturisticos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.pacotesturisticos.dao.IAgenciaDAO;
import br.ufscar.dc.dsw.pacotesturisticos.dao.IPacoteDAO;
import br.ufscar.dc.dsw.pacotesturisticos.dao.IClienteDAO;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Cliente;

@SpringBootApplication
public class PacotesturisticosApplication {

	public static void main(String[] args) {
		SpringApplication.run(PacotesturisticosApplication.class, args);
	}
	
	/*
	 * @Bean public CommandLineRunner demo(IClienteDAO clienteDAO,
	 * BCryptPasswordEncoder encoder, IAgenciaDAO agenciaDAO, IPacoteDAO pacoteDAO)
	 * { return (args) -> {
	 * 
	 * Cliente u1 = new Cliente(); u1.setEmail("admin@gmail.com");
	 * u1.setSenha(encoder.encode("admin")); u1.setCpf("012.345.678-90");
	 * u1.setNome("Administrador"); u1.setTipo("ROLE_ADMIN"); //u1.setEnabled(true);
	 * clienteDAO.save(u1);
	 * 
	 * Cliente u2 = new Cliente(); u2.setEmail("beltrano@gmail.com");
	 * u2.setSenha(encoder.encode("123")); u2.setCpf("985.849.614-10");
	 * u2.setNome("Beltrano Andrade"); u2.setTipo("ROLE_USER");
	 * //u2.setEnabled(true); clienteDAO.save(u2); }; }
	 */

}
