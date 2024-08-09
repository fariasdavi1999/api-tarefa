package com.backendcrudangular.backendcrudangular.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendcrudangular.backendcrudangular.model.Cliente;
import com.backendcrudangular.backendcrudangular.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @CrossOrigin(origins = "*")
    @GetMapping("/listar-clientes")
    public List<Cliente> listarTodos() {

        return clienteService.listarTodos();

    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public Optional<Cliente> listarPorId(@PathVariable Long id) {

        return clienteService.listarPorId(id);
    }

    @CrossOrigin(origins = "*")
    @PostMapping
    public Cliente salvar(@RequestBody Cliente cliente) {

        return clienteService.salvar(cliente);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Long id) {
        clienteService.deletar(id);
        return "Cliente com id: " + id + " Deletado com Sucesso";
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/{id}")
    public Cliente alterar(@RequestBody Cliente cliente, @PathVariable Long id) {
        return clienteService.alterar(id, cliente);
    }

}
