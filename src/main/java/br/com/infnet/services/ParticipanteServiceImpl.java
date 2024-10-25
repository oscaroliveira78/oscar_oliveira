package br.com.infnet.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.infnet.exceptions.NegocioException;
import br.com.infnet.exceptions.TabelaDeErros;
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
		if (!participanteRepository.existsById(participante.getId())) {
			throw new NegocioException(TabelaDeErros.REGISTRO_NAO_ENCONTRADO);
		}
		participanteRepository.save(participante);
	}

	@Override
	public void deletarParticipante(Long id) {
		if (!participanteRepository.existsById(id)) {
			throw new NegocioException(TabelaDeErros.REGISTRO_NAO_ENCONTRADO);
		}
		participanteRepository.deleteById(id);
	}

	@Override
	public Participante buscarParticipantePorId(Long id) {
		return participanteRepository.findById(id).orElseThrow(() -> new NegocioException(TabelaDeErros.REGISTRO_NAO_ENCONTRADO));
	}

	@Override
	public List<Participante> listarParticipantes() {
		List<Participante> participantes = participanteRepository.findAll();
		if (participantes.isEmpty()) {
			throw new NegocioException(TabelaDeErros.REGISTRO_NAO_ENCONTRADO);
		}
		return participantes;
	}
}
