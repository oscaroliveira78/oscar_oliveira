package br.com.infnet.controllers.openapi;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.infnet.models.Participante;

public interface ParticipanteControllerOpenApi {

	public List<Participante> listarParticipantes();

	public ResponseEntity<Participante> registrarParticipante(@RequestBody Participante participante);

	public ResponseEntity<Participante> atualizarParticipante(@PathVariable Long id, @RequestBody Participante participante);

	public ResponseEntity<Void> deletarParticipante(@PathVariable Long id);

	public ResponseEntity<Participante> buscarParticipantePorId(@PathVariable Long id);
}
