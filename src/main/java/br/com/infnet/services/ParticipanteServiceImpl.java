package br.com.infnet.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.infnet.models.Participante;
import br.com.infnet.repositorys.ParticipanteRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParticipanteServiceImpl implements ParticipanteManager {
	private final ParticipanteRepository participanteRepository;

	@Override
	public void registrarParticipante(Participante participante) {
		participanteRepository.save(participante);
	}

	@Override
	public void atualizarParticipante(Participante participante) {
		participanteRepository.save(participante);
	}

	@Override
	public void deletarParticipante(Long id) {
		participanteRepository.deleteById(id);
	}

	@Override
	public Participante buscarParticipantePorId(Long id) {
		return participanteRepository.findById(id).orElse(null);
	}

	@Override
	public List<Participante> listarParticipantes() {
		return participanteRepository.findAll();
	}
}