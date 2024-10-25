package br.com.infnet.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
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

import br.com.infnet.controllers.openapi.EventoControllerOpenApi;
import br.com.infnet.models.Evento;
import br.com.infnet.services.EventoServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/eventos")
@RequiredArgsConstructor
@CrossOrigin
public class EventoController implements EventoControllerOpenApi {

	private final EventoServiceImpl eventoService;

	@PostMapping
	public ResponseEntity<Evento> criarEvento(@Valid @RequestBody Evento evento) {

		eventoService.criarEvento(evento);
		return ResponseEntity.status(HttpStatus.CREATED).body(evento);
	}

	@GetMapping
	public ResponseEntity<List<Evento>> listarEventos() {

		List<Evento> eventos = eventoService.listarEventos();
		return ResponseEntity.ok(eventos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Evento> buscarEventoPorId(@PathVariable Long id) {

		Evento evento = eventoService.buscarEventoPorId(id);
		if (evento != null) {
			return ResponseEntity.ok(evento);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Evento> atualizarEvento(@PathVariable Long id, @Valid @RequestBody Evento eventoAtualizado) {

		Evento eventoExistente = eventoService.buscarEventoPorId(id);
		if (eventoExistente != null) {
			eventoAtualizado.setId(id); // Garante que o ID seja o mesmo do evento existente
			eventoService.atualizarEvento(eventoAtualizado);
			return ResponseEntity.ok(eventoAtualizado);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarEvento(@PathVariable Long id) {

		Evento evento = eventoService.buscarEventoPorId(id);
		if (evento != null) {
			eventoService.deletarEvento(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@PostMapping("/{id}/vender-ingresso")
	public ResponseEntity<String> venderIngresso(@PathVariable Long id) {

		try {
			eventoService.venderIngresso(id);
			return ResponseEntity.ok("Ingresso vendido com sucesso!");
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}