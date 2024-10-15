package br.com.infnet.services;

import java.util.List;

import br.com.infnet.models.Participante;

public interface ParticipanteManager {

	void registrarParticipante(Participante participante);

	void atualizarParticipante(Participante participante);

	void deletarParticipante(Long id);

	Participante buscarParticipantePorId(Long id);

	List<Participante> listarParticipantes();
}