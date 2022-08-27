package br.ufscar.dc.dsw.pacotesturisticos.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Base64;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
import org.springframework.web.multipart.MultipartFile;

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
    public String cadastro(Pacote pacote, MultipartFile[] imagemFile, ModelMap model) {
        return "pacote/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model){
        model.addAttribute("agencias", agenciaService.findAll());
        model.addAttribute("pacotes", pacoteService.findAll());
        return "pacote/lista";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute(value="pacote") @Valid Pacote pacote, BindingResult resultPacote, @ModelAttribute("imagemFile") MultipartFile[] imagemFile, BindingResult resultImagemFile, RedirectAttributes attributes, Authentication authentication) {
        if (resultPacote.hasErrors() || resultImagemFile.hasErrors()) {
            return "pacote/cadastro";
        }
        Agencia userAgencia = getAgencia();
        pacote.setAgencia(userAgencia);
        pacoteService.save(pacote);
        try{
            for (MultipartFile multipartFile : imagemFile) {
                byte[] img = multipartFile.getBytes();
                Imagem imagem = new Imagem();
                imagem.setTipo(multipartFile.getContentType());
                imagem.setByteStream(img);
                imagem.setPacote(pacote);
                imagemService.save(imagem);
            }
        } catch (IOException e) {
                e.printStackTrace();
        }
        attributes.addFlashAttribute("sucess", "Pacote salvo com sucesso!");
        return "redirect:/pacote/cadastrar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model, RedirectAttributes attributes) {
        Pacote pacote = pacoteService.findById(id);
        Agencia userAgencia = getAgencia();
        if(pacote.getAgencia().getId().compareTo(userAgencia.getId()) != 0){
            attributes.addFlashAttribute("fail", "Você não pode editar um pacote que não seja seu!");
            return "redirect:/pacote/listar";
        }
        model.addAttribute("pacote", pacote);
        return "pacote/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@ModelAttribute(value="pacote") @Valid Pacote pacote, BindingResult resultPacote, @ModelAttribute("imagemFile[]") MultipartFile[] imagemFile, @ModelAttribute("apagadas") String imagemApagar, BindingResult resultImagemFile,  RedirectAttributes attributes, Authentication authentication) {
        if (resultPacote.hasErrors() || resultImagemFile.hasErrors()) {
            return "pacote/cadastro";
        }
        Agencia userAgencia = getAgencia();
        if(pacote.getAgencia().getId().longValue() != userAgencia.getId().longValue()){
            attributes.addFlashAttribute("fail", pacote.getAgencia().getId().toString() + " " + userAgencia.getId().toString());
            return "redirect:/pacote/listar";
        }
        pacoteService.save(pacote);
        try{
            for (MultipartFile multipartFile : imagemFile) {
                byte[] img = multipartFile.getBytes();
                String tipo = multipartFile.getContentType();
                if(tipo.contains("image")){
                    Imagem imagem = new Imagem();
                    imagem.setTipo(tipo);
                    imagem.setByteStream(img);
                    imagem.setPacote(pacote);
                    imagemService.save(imagem);
                }
            }
        } catch (IOException e) {
                e.printStackTrace();
        }
        //imagemApagar ="1,2,3" -> 1,2,3
        if(!imagemApagar.isEmpty()){
            String[] imagensApagar = imagemApagar.split(",");
            for(String id : imagensApagar){
                imagemService.deleteById(Long.parseLong(id));
            }
        }
        attributes.addFlashAttribute("sucess", "Pacote editado com sucesso!");
        return "redirect:/pacote/cadastrar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, ModelMap model, RedirectAttributes attributes) {
        
        for(Imagem imagem : imagemService.findByPacote(pacoteService.findById(id))){
            imagemService.deleteById(imagem.getId());
        }
        for(Compra compra : compraService.findByPacote(pacoteService.findById(id))){
            compraService.deleteById(compra.getId());
        }
        pacoteService.deleteById(id);
        attributes.addFlashAttribute("mensagem", "Pacote excluido com sucesso!");
        return "redirect:/pacote/listar";
    }

    @GetMapping("/meus-pacotes")
    public String meusPacotes(ModelMap model, Authentication authentication){
        UsuarioDetails usuarioDetails = (UsuarioDetails) authentication.getPrincipal();
        model.addAttribute("pacotes", pacoteService.findByAgencia(usuarioDetails.getAgencia()));
        return "pacote/lista";
    }

    private Agencia getAgencia() {
    UsuarioDetails userDetails = (UsuarioDetails) (UsuarioDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getAgencia();
    }
}
