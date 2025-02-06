package org.repaso.controller;

import jakarta.validation.Valid;
import org.repaso.dto.PedidoDTO;
import org.repaso.model.Cliente;
import org.repaso.model.Comercial;
import org.repaso.service.ComercialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/comerciales")
public class ComercialController {
    private ComercialService comercialService;

    public ComercialController(ComercialService comercialService) {
        this.comercialService = comercialService;
    }

    @GetMapping()
    public String listar(Model model) {
        List<Comercial> listaComerciales =  comercialService.listAll();
        model.addAttribute("listaComerciales", listaComerciales);

        return "comerciales/comerciales";
    }

    @GetMapping("/{id}")
    public String detalle(Model model, @PathVariable Integer id ) {

        Comercial comercial = comercialService.one(id);
        model.addAttribute("comercial", comercial);

        List<PedidoDTO> pedidosDTO = comercialService.listPedidosDTO(id);
        model.addAttribute("pedidosDTO", pedidosDTO);

        int totalPedidos = comercialService.getTotalPedidos();
        model.addAttribute("totalPedidos", totalPedidos);

        double porcentajePedidos = comercialService.getPorcentajePedidos(id);
        model.addAttribute("porcentajePedidos", porcentajePedidos);

        return "comerciales/detalles";
    }

    @GetMapping("/crear")
    public String crear(Model model) {

        Comercial comercial = new Comercial();
        model.addAttribute("comercial", comercial);

        return "comerciales/crear";

    }

    @PostMapping("/crear")
    public String submitCrear(@Valid @ModelAttribute Comercial comercial, BindingResult bindingResulted, Model model) {

        if (bindingResulted.hasErrors()) {
            model.addAttribute("comercial", comercial);
            return "/comerciales/crear";
        }
        comercialService.newComercial(comercial);

        return "/comerciales";

    }

    @GetMapping("/editar/{id}")
    public String editar(Model model, @PathVariable Integer id) {

        Comercial comercial = comercialService.one(id);
        model.addAttribute("comercial", comercial);

        return "comerciales/editar";

    }

    @PostMapping("/editar/{id}")
    public String submitEditar(@Valid @ModelAttribute Comercial comercial, BindingResult bindingResulted, @PathVariable Integer id, Model model) {

        if (bindingResulted.hasErrors()) {
            model.addAttribute("comercial", comercial);
            model.addAttribute("id", id);

            return "/comerciales/editar";
        }
        comercialService.updateComercial(comercial);

        return "/comerciales";
    }

    @PostMapping("/borrar/{id}")
    public RedirectView submitBorrar(@PathVariable Integer id) {

        comercialService.deleteComercial(id);

        return new RedirectView("/comerciales");
    }
}
