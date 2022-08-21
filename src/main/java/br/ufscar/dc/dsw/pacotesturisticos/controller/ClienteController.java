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
import br.ufscar.dc.dsw.pacotesturisticos.domain.Cliente;
import br.ufscar.dc.dsw.pacotesturisticos.service.spec.IClienteService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
    
    @Autowired
    private IClienteService clienteService;

    @GetMapping("/cadastrar")
    public String cadastro(Cliente cliente) {
        return "cliente/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model){
        model.addAttribute("clientes", clienteService.findAll());
        return "cliente/lista";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "cliente/cadastro";
        }
        clienteService.save(cliente);
        attributes.addFlashAttribute("sucess", "Cliente salvo com sucesso!");
        return "redirect:/cliente/cadastro";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("cliente", clienteService.findById(id));
        return "cliente/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "cliente/cadastro";
        }
        clienteService.save(cliente);
        attributes.addFlashAttribute("sucess", "Cliente editado com sucesso!");
        return "redirect:/cliente/cadastro";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, ModelMap model) {
        if(clienteService.clienteTemCompras(id)){
            model.addAttribute("error", "Cliente não pode ser excluído pois tem compras.");
            return "redirect:/cliente/lista";
        } else {
            clienteService.deleteById(id);
            model.addAttribute("sucess", "Cliente excluído com sucesso!");
        }
        return "redirect:/cliente/lista";
    }
}
