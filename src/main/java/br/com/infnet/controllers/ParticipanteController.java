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

import br.com.infnet.controllers.openapi.ParticipanteControllerOpenApi;
import br.com.infnet.models.Participante;
import br.com.infnet.services.ParticipanteServiceImpl;

@RestController
@RequestMapping("/participantes")
@CrossOrigin
public class ParticipanteController implements ParticipanteControllerOpenApi {

	@Autowired
	private ParticipanteServiceImpl participanteService;

	@GetMapping
	public List<Participante> listarParticipantes() {
		return participanteService.listarParticipantes();
	}

	@PostMapping
	public ResponseEntity<Participante> registrarParticipante(@RequestBody Participante participante) {

		participanteService.registrarParticipante(participante);
		return ResponseEntity.ok(participante);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Participante> atualizarParticipante(@PathVariable Long id, @RequestBody Participante participante) {

		participante.setId(id);
		participanteService.atualizarParticipante(participante);
		return ResponseEntity.ok(participante);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarParticipante(@PathVariable Long id) {

		participanteService.deletarParticipante(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Participante> buscarParticipantePorId(@PathVariable Long id) {

		Participante participante = participanteService.buscarParticipantePorId(id);
		return participante != null ? ResponseEntity.ok(participante) : ResponseEntity.notFound().build();
	}
}