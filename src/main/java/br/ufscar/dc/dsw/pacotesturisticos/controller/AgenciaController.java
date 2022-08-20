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
import br.ufscar.dc.dsw.pacotesturisticos.domain.Agencia;
import br.ufscar.dc.dsw.pacotesturisticos.service.spec.IAgenciaService;

@Controller
@RequestMapping("/agencia")
public class AgenciaController {
    
    @Autowired
    private IAgenciaService agenciaService;

    @GetMapping("/cadastro")
    public String cadastro(Agencia agencia) {
        return "agencia/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model){
        model.addAttribute("agencias", agenciaService.findAll());
        return "agencia/lista";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Agencia agencia, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "agencia/cadastro";
        }
        agenciaService.save(agencia);
        attributes.addFlashAttribute("sucess", "Agencia salva com sucesso!");
        return "redirect:/agencia/cadastro";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("agencia", agenciaService.findById(id));
        return "agencia/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@Valid Agencia agencia, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "agencia/cadastro";
        }
        agenciaService.save(agencia);
        attributes.addFlashAttribute("sucess", "Agencia editada com sucesso!");
        return "redirect:/agencia/cadastro";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, ModelMap model) {
        if(agenciaService.agenciaTemPacotes(id)){
            model.addAttribute("fail", "Agencia não pode ser excluída pois possui pacotes associados!");
        } else {
            agenciaService.deleteById(id);
            model.addAttribute("mensagem", "Agencia excluida com sucesso!");
        }
        return "redirect:/agencia/listar";
    }

}
