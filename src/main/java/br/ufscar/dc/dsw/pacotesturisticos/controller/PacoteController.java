package br.ufscar.dc.dsw.pacotesturisticos.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Pacote;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Imagem;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Agencia;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Compra;
import br.ufscar.dc.dsw.pacotesturisticos.service.spec.IPacoteService;
import br.ufscar.dc.dsw.pacotesturisticos.service.spec.IImagemService;
import br.ufscar.dc.dsw.pacotesturisticos.service.spec.IAgenciaService;
import br.ufscar.dc.dsw.pacotesturisticos.service.spec.ICompraService;

import br.ufscar.dc.dsw.pacotesturisticos.security.UsuarioDetails;

@Controller
@RequestMapping("/pacote")
public class PacoteController {
    
    @Autowired
    private IPacoteService pacoteService;
    @Autowired
    private IImagemService imagemService;
    @Autowired
    private IAgenciaService agenciaService;
    @Autowired
    private ICompraService compraService;

    @GetMapping("/cadastrar")
    public String cadastro(Pacote pacote, ModelMap model) {
        List<Imagem> imagens = new ArrayList<Imagem>();
        for(int i = 0; i < 10; i++){
            Imagem imagem = new Imagem();
            imagem.setLink("");
            imagem.setPacote(pacote);
            imagens.add(imagem);
        }
        pacote.setImagens(imagens);
        return "pacote/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model){
        model.addAttribute("agencias", agenciaService.findAll());
        model.addAttribute("pacotes", pacoteService.findAll());
        return "pacote/lista";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Pacote pacote, BindingResult result, RedirectAttributes attributes, Authentication authentication) {
        if (result.hasErrors()) {
            return "pacote/cadastro";
        }
        UsuarioDetails userDetails = (UsuarioDetails) authentication.getPrincipal();
        Agencia agencia = userDetails.getAgencia();
        pacote.setAgencia(agencia);
        pacoteService.save(pacote);
        attributes.addFlashAttribute("sucess", "Pacote salvo com sucesso!");
        return "redirect:/pacote/cadastrar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        Pacote pacote = pacoteService.findById(id);
        model.addAttribute("pacote", pacote);
        return "pacote/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@Valid Pacote pacote, BindingResult result, RedirectAttributes attributes, Authentication authentication) {
        if (result.hasErrors()) {
            return "pacote/cadastro";
        }
        UsuarioDetails userDetails = (UsuarioDetails) authentication.getPrincipal();
        Agencia agencia = userDetails.getAgencia();
        pacote.setAgencia(agencia);
        pacoteService.save(pacote);
        attributes.addFlashAttribute("sucess", "Pacote editado com sucesso!");
        return "redirect:/pacote/cadastrar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, ModelMap model) {
        for(Compra compra : compraService.findByPacote(pacoteService.findById(id))){
            compraService.deleteById(compra.getId());
        }
        for(Imagem imagem : imagemService.findByPacote(id)){
            imagemService.deleteById(imagem.getId());
        }
        pacoteService.deleteById(id);
        return "redirect:/pacote/lista";
    }

    @GetMapping("/meus-pacotes")
    public String meusPacotes(ModelMap model, Authentication authentication){
        UsuarioDetails usuarioDetails = (UsuarioDetails) authentication.getPrincipal();
        model.addAttribute("pacotes", pacoteService.findByAgencia(usuarioDetails.getAgencia()));
        return "pacote/lista";
    }
}
