package br.ufscar.dc.dsw.pacotesturisticos.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Cliente;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Compra;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Agencia;
import br.ufscar.dc.dsw.pacotesturisticos.service.spec.IClienteService;
import br.ufscar.dc.dsw.pacotesturisticos.service.spec.ICompraService;
import br.ufscar.dc.dsw.pacotesturisticos.service.spec.IAgenciaService;


@Controller
@RequestMapping("/cliente")
public class ClienteController {
    
    @Autowired
    private IClienteService clienteService;

    @Autowired
    private ICompraService compraService;
    
    @Autowired
    private IAgenciaService agenciaService;

    @Autowired
    private BCryptPasswordEncoder encoder;

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
        Cliente cliente2 = clienteService.findByEmail(cliente.getEmail());
        Agencia agencia2 = agenciaService.findByEmail(cliente.getEmail());
        if (cliente2 != null || agencia2 != null) {
            attributes.addFlashAttribute("fail", "E-mail já cadastrado!");
            return "redirect:/cliente/cadastrar";
        }
        cliente2 = clienteService.findByCpf(cliente.getCpf());
        if (cliente2 != null) {
            attributes.addFlashAttribute("fail", "CPF já cadastrado!");
            return "redirect:/cliente/cadastrar";
        }
        cliente.setSenha(encoder.encode(cliente.getSenha()));
        clienteService.save(cliente);
        attributes.addFlashAttribute("sucess", "Cliente salvo com sucesso!");
        return "redirect:/cliente/cadastrar";
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
        Cliente cliente2 = clienteService.findByEmail(cliente.getEmail());
        Agencia agencia2 = agenciaService.findByEmail(cliente.getEmail());
        if ((cliente2 != null && !cliente2.getId().equals(cliente.getId())) || agencia2 != null) {
            attributes.addFlashAttribute("fail", "E-mail já cadastrado!");
            return "redirect:/cliente/cadastrar";
        }
        cliente2 = clienteService.findByCpf(cliente.getCpf());
        if (cliente2 != null && !cliente2.getId().equals(cliente.getId())) {
            attributes.addFlashAttribute("fail", "CPF já cadastrado!");
            return "redirect:/cliente/cadastrar";
        }
        clienteService.save(cliente);
        attributes.addFlashAttribute("sucess", "Cliente editado com sucesso!");
        return "redirect:/cliente/cadastrar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, ModelMap model) {
            for(Compra compra : compraService.findByCliente(clienteService.findById(id))) {
                compraService.deleteById(compra.getId());
            }
            clienteService.deleteById(id);
            model.addAttribute("sucess", "Cliente excluído com sucesso!");
        return "redirect:/cliente/listar";
    }
}
