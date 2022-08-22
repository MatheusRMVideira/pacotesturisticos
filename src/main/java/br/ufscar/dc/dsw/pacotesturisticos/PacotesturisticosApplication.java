package br.ufscar.dc.dsw.pacotesturisticos;

//import org.hibernate.mapping.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.pacotesturisticos.dao.IAgenciaDAO;
import br.ufscar.dc.dsw.pacotesturisticos.dao.IClienteDAO;
import br.ufscar.dc.dsw.pacotesturisticos.dao.IImagemDAO;
import br.ufscar.dc.dsw.pacotesturisticos.dao.IPacoteDAO;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Agencia;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Cliente;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Imagem;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Pacote;
import java.util.List;


@SpringBootApplication
public class PacotesturisticosApplication {

	public static void main(String[] args) {
		SpringApplication.run(PacotesturisticosApplication.class, args);
	}
	
	
	  @Bean public CommandLineRunner demo(IClienteDAO clienteDAO,
	  BCryptPasswordEncoder encoder, IAgenciaDAO agenciaDAO, IPacoteDAO pacoteDAO, IImagemDAO imagemDAO)
	  { return (args) -> {
	  
				
				
				/*
				 * Cliente u1 = new Cliente(); u1.setEmail("admin@gmail.com");
				 * u1.setSenha(encoder.encode("admin")); u1.setCpf("012.345.678-90");
				 * u1.setNome("Administrador"); u1.setTelefone("123456789"); u1.setSexo('F');
				 * u1.setDataNascimento("1995-02-10"); u1.setTipo("ROLE_ADMIN");
				 * //u1.setEnabled(true); clienteDAO.save(u1);
				 * 
				 * 
				 * Cliente u2 = new Cliente(); u2.setCpf("985.849.614-10");
				 * u2.setDataNascimento("1995-10-10"); u2.setEmail("beltrano@gmail.com");
				 * u2.setNome("Beltrano Andrade"); u2.setSenha(encoder.encode("123"));
				 * u2.setSexo('M'); u2.setTelefone("987654321"); u2.setTipo("ROLE_USER");
				 * clienteDAO.save(u2);
				 * 
				 * Agencia a1 = new Agencia(); a1.setCnpj("12.123.123/1231-23");
				 * a1.setDescricao("Melhor Agencia"); a1.setEmail("agencia1@gmail.com");
				 * a1.setNome("agencia1"); a1.setSenha("agencia1"); agenciaDAO.save(a1);
				 * 
				 * Imagem m1 = new Imagem();
				 * m1.setLink("https://www.oimenu.com.br/cardapio-digital-sao-carlos-sp");
				 * imagemDAO.save(m1);
				 * 
				 * Imagem m2 = new Imagem(); m2.setLink(
				 * "https://saocarlosemrede.com.br/indice-de-isolamento-social-dispara-em-sao-carlos/"
				 * ); imagemDAO.save(m2);
				 */
				  
				/*
				 * List<Imagem> imagens = null; imagens.add(m1); imagens.add(m2);
				 */
				 
				/*
				 * Pacote p1 = new Pacote(); p1.setAgencia(a1); p1.setCidade("São Carlos");
				 * p1.setDescricao("Capital da Tecnologia"); p1.setDuracao(10);
				 * p1.setEstado("São Paulo"); p1.setPais("Brasil"); p1.setPartida("2022-10-09");
				 * //p1.setPreco("1050.00"); p1.setImagens(imagens); pacoteDAO.save(p1);
				 */
				  
				  //u2.setEnabled(true); 
				 
		  }; 
	  }
	 

}
