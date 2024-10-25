package br.com.infnet.controllers;

import java.util.List;

import javax.validation.Valid;

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

import br.com.infnet.controllers.openapi.IngressoControllerOpenApi;
import br.com.infnet.models.Ingresso;
import br.com.infnet.services.IngressoServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/ingressos")
@RequiredArgsConstructor
@CrossOrigin
public class IngressoController implements IngressoControllerOpenApi {

	private final IngressoServiceImpl ingressoService;

	@PostMapping
	public ResponseEntity<Ingresso> emitirIngresso(@Valid @RequestBody Ingresso ingresso) {

		ingressoService.emitirIngresso(ingresso);
		return ResponseEntity.ok(ingresso);
	}

	@GetMapping
	public ResponseEntity<List<Ingresso>> listarIngressos() {

		List<Ingresso> ingressos = ingressoService.listarIngressos();
		return ResponseEntity.ok(ingressos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Ingresso> buscarIngressoPorId(@PathVariable Long id) {

		Ingresso ingresso = ingressoService.buscarIngressoPorId(id);
		return ingresso != null ? ResponseEntity.ok(ingresso) : ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Ingresso> atualizarIngresso(@PathVariable Long id, @Valid @RequestBody Ingresso ingresso) {

		ingresso.setId(id);
		ingressoService.atualizarIngresso(ingresso);
		return ResponseEntity.ok(ingresso);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarIngresso(@PathVariable Long id) {

		ingressoService.deletarIngresso(id);
		return ResponseEntity.noContent().build();
	}
}
