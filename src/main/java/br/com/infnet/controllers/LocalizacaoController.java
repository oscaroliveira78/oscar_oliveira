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

import br.com.infnet.models.Localizacao;
import br.com.infnet.services.LocalizacaoServiceImpl;

@RestController
@RequestMapping("/localizacoes")
@CrossOrigin
public class LocalizacaoController {

	@Autowired
	private LocalizacaoServiceImpl localizacaoService;

	@GetMapping
	public List<Localizacao> listarLocalizacoes() {
		
		return localizacaoService.listarLocalizacoes();
	}

	@PostMapping
	public ResponseEntity<Localizacao> criarLocalizacao(@RequestBody Localizacao localizacao) {
		
		localizacaoService.criarLocalizacao(localizacao);
		return ResponseEntity.ok(localizacao);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Localizacao> atualizarLocalizacao(@PathVariable Long id, @RequestBody Localizacao localizacao) {
		
		localizacao.setId(id);
		localizacaoService.atualizarLocalizacao(localizacao);
		return ResponseEntity.ok(localizacao);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarLocalizacao(@PathVariable Long id) {
		
		localizacaoService.deletarLocalizacao(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Localizacao> buscarLocalizacaoPorId(@PathVariable Long id) {
		
		Localizacao localizacao = localizacaoService.buscarLocalizacaoPorId(id);
		return localizacao != null ? ResponseEntity.ok(localizacao) : ResponseEntity.notFound().build();
	}
}
