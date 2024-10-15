package br.com.infnet.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.infnet.models.Organizador;
import br.com.infnet.services.OrganizadorServiceImpl;

@RestController
@RequestMapping("/organizadores")
@CrossOrigin
public class OrganizadorController {

    @Autowired
    private OrganizadorServiceImpl organizadorService;

    @GetMapping
    public List<Organizador> listarOrganizadores() {
    	
        return organizadorService.listarOrganizadores();
    }

    @PostMapping
    public ResponseEntity<Organizador> criarOrganizador(@RequestBody Organizador organizador) {
    	
        organizadorService.criarOrganizador(organizador);
        return ResponseEntity.ok(organizador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Organizador> atualizarOrganizador(@PathVariable Long id, @RequestBody Organizador organizador) {
    	
        organizador.setId(id);
        organizadorService.atualizarOrganizador(organizador);
        return ResponseEntity.ok(organizador);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarOrganizador(@PathVariable Long id) {
    	
        organizadorService.deletarOrganizador(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Organizador> buscarOrganizadorPorId(@PathVariable Long id) {
    	
        Organizador organizador = organizadorService.buscarOrganizadorPorId(id);
        return organizador != null ? ResponseEntity.ok(organizador) : ResponseEntity.notFound().build();
    }
}
