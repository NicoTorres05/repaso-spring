package org.repaso.controller;

import jakarta.validation.Valid;
import org.repaso.dao.ClienteDAO;
import org.repaso.dto.PedidoDTO;
import org.repaso.model.Cliente;
import org.repaso.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@Controller

@RequestMapping("/clientes")
public class ClienteController {
    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping()
    public String listarClientes(Model model) {
        List<Cliente> listaClientes = clienteService.listAll();
        model.addAttribute("listaClientes", listaClientes);

        return "clientes/clientes";
    }

    @GetMapping("/{id}")
    public String detalle(Model model, @PathVariable int id) {
        Cliente cliente = clienteService.one(id);
        model.addAttribute("cliente", cliente);

        List<PedidoDTO> pedidosDTO = clienteService.listPedidosDTO(id);
        model.addAttribute("pedidosDTO", pedidosDTO);

        return "clientes/detalles";
    }

    @GetMapping("/crear")
    public String crear(Model model) {
        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);

        return "clientes/crear";
    }

    @PostMapping("/crear")
    public String submitCrear(@Valid @ModelAttribute Cliente cliente, BindingResult bindingResulted, Model model) {
        if (bindingResulted.hasErrors()) {
            model.addAttribute("cliente", cliente);

            return "clientes/crear";
        }
        clienteService.newCliente(cliente);

        return "/clientes";
    }

    @GetMapping("/editar/{id}")
    public String editar(Model model, @PathVariable Integer id) {

        Cliente cliente = clienteService.one(id);
        model.addAttribute("cliente", cliente);

        return "clientes/editar";

    }

    @PostMapping("/editar/{id}")
    public String submitEditar(@Valid @ModelAttribute Cliente cliente, BindingResult bindingResulted, @PathVariable Integer id, Model model) {
        if (bindingResulted.hasErrors()) {
            model.addAttribute("cliente", cliente);
            model.addAttribute("id", id);

            return "/clientes/editar";
        }
        clienteService.updateCliente(cliente);

        return "/clientes";
    }

    @PostMapping("/borrar/{id}")
    public RedirectView borrar(@PathVariable Integer id) {
        clienteService.deleteCliente(id);

        return new RedirectView("/clientes");
    }

}
