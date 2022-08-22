package br.ufscar.dc.dsw.pacotesturisticos.controller;

import java.util.ArrayList;

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
import br.ufscar.dc.dsw.pacotesturisticos.service.spec.IPacoteService;
import br.ufscar.dc.dsw.pacotesturisticos.service.spec.IImagemService;

import br.ufscar.dc.dsw.pacotesturisticos.security.UsuarioDetails;

@Controller
@RequestMapping("/pacote")
public class PacoteController {
    
    @Autowired
    private IPacoteService pacoteService;
    @Autowired
    private IImagemService imagemService;

    @GetMapping("/cadastrar")
    public String cadastro(Pacote pacote, ModelMap model) {
        return "pacote/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model){
        model.addAttribute("pacotes", pacoteService.findAll());
        return "pacote/lista";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Pacote pacote, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "pacote/cadastro";
        }
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
    public String editar(@Valid Pacote pacote, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "pacote/cadastro";
        }
        pacoteService.save(pacote);
        attributes.addFlashAttribute("sucess", "Pacote editado com sucesso!");
        return "redirect:/pacote/cadastrar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, ModelMap model) {
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
