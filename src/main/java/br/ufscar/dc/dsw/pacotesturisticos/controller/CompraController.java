package br.ufscar.dc.dsw.pacotesturisticos.controller;

import java.time.LocalDate;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.core.context.SecurityContextHolder;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Compra;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Pacote;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Agencia;
import br.ufscar.dc.dsw.pacotesturisticos.security.UsuarioDetails;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Cliente;
import br.ufscar.dc.dsw.pacotesturisticos.service.spec.ICompraService;
import br.ufscar.dc.dsw.pacotesturisticos.service.spec.IPacoteService;
import br.ufscar.dc.dsw.pacotesturisticos.service.spec.IAgenciaService;

@Controller
@RequestMapping("/compra")
public class CompraController {
    
    @Autowired
    private ICompraService compraService;

    @Autowired
    private IPacoteService pacoteService;

    @Autowired
    private IAgenciaService agenciaService;

    @GetMapping("/detalhes/{id}")
    public String detalhes(@PathVariable("id") Long id, Compra compra,ModelMap model) {
        compra.setPacote(pacoteService.findById(id));
        compra.setCliente(getUsuario());
        return "compra/detalhes";
    }

    @PostMapping("/insere")
    public String insere(Compra compra, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "compra/detalhes";
        }
        compra.setAtivo(true);
        compraService.save(compra);
        attributes.addFlashAttribute("sucess", "Compra realizada com sucesso!");
        return "redirect:/compra/listar";
    }

    private Cliente getUsuario() {
        UsuarioDetails usuarioDetails = (UsuarioDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return usuarioDetails.getCliente();
    }

    @GetMapping("/listar")
    public String listar(ModelMap model){
        model.addAttribute("agencia", agenciaService.findAll());
        model.addAttribute("compras", compraService.findByCliente(this.getUsuario()));
        return "compra/lista";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, ModelMap model) {
        compraService.deleteById(id);
        return "redirect:/compra/listar";
    }

    @GetMapping("/cancelar/{id}")
    public String cancelar(@PathVariable("id") Long id, ModelMap model, RedirectAttributes attributes) {
        Compra compra = compraService.findById(id);
        LocalDate dataPartida = LocalDate.parse(compra.getPacote().getPartida());
        if(dataPartida.isBefore(LocalDate.now())){
            attributes.addFlashAttribute("fail", "Não é possível cancelar uma viagem que já aconteceu");
            return "redirect:/compra/listar";
        }
        dataPartida = dataPartida.minusDays(5);
        if(dataPartida.isBefore(LocalDate.now())){
            attributes.addFlashAttribute("fail", "Só é possível cancelar uma viagem com 5 dias de antecedência");
            return "redirect:/compra/listar";
        }
        compra.setAtivo(false);
        compraService.save(compra);
        return "redirect:/compra/listar";
    }

}
