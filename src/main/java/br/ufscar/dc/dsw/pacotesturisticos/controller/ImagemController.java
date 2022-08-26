package br.ufscar.dc.dsw.pacotesturisticos.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
@RequestMapping("/imagem")
public class ImagemController {

    @Autowired
    private IImagemService imagemService;

    @Autowired
    private IPacoteService pacoteService;

    @PutMapping("/salvar/{id}")
    public void uploadImagem(@RequestParam("imagens") MultipartFile[] imagemFile, @PathVariable("id") Long pacoteId){
        try {
            for (MultipartFile multipartFile : imagemFile) {
                byte[] img = multipartFile.getBytes();
                Imagem imagem = new Imagem();
                imagem.setByteStream(img);
                imagem.setPacote(pacoteService.findById(pacoteId));
                imagemService.save(imagem);
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @GetMapping("/listar/{id}")
    public String listar(@PathVariable("id") Long pacoteId, ModelMap model){
        Pacote pacote = pacoteService.findById(pacoteId);
        model.addAttribute("pacote", pacote);
        model.addAttribute("imagens", pacote.getImagens());
        return "imagem/listar";
    }
}