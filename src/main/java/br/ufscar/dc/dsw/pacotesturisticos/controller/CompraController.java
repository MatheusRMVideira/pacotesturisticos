package br.ufscar.dc.dsw.pacotesturisticos.controller;

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
import br.ufscar.dc.dsw.pacotesturisticos.security.UsuarioDetails;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Cliente;
import br.ufscar.dc.dsw.pacotesturisticos.service.spec.ICompraService;
import br.ufscar.dc.dsw.pacotesturisticos.service.spec.IPacoteService;

@Controller
@RequestMapping("/compra")
public class CompraController {
    
    @Autowired
    private ICompraService compraService;

    @Autowired
    private IPacoteService pacoteService;

    @GetMapping("/detalhes/{id}")
    public String detalhes(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("pacote", pacoteService.findById(id));
        return "compra/detalhes";
    }

    @PostMapping("/inserir")
    public String insere(@Valid Compra compra, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "compra/detalhes";
        }
        compraService.save(compra);
        attributes.addFlashAttribute("sucess", "Compra realizada com sucesso!");
        return "redirect:/compra/detalhes";
    }

    private Cliente getUsuario() {
        UsuarioDetails usuarioDetails = (UsuarioDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return usuarioDetails.getCliente();
    }

    @GetMapping("/listar")
    public String listar(ModelMap model){
        model.addAttribute("compras", compraService.findByCliente(this.getUsuario()));
        return "compra/lista";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, ModelMap model) {
        compraService.deleteById(id);
        return "redirect:/compra/detalhes";
    }

}
