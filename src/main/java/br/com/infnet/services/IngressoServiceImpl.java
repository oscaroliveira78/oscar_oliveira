package br.com.infnet.services;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.infnet.exceptions.NegocioException;
import br.com.infnet.exceptions.TabelaDeErros;
import br.com.infnet.models.Evento;
import br.com.infnet.models.Ingresso;
import br.com.infnet.models.Participante;
import br.com.infnet.repositorys.EventoRepository;
import br.com.infnet.repositorys.IngressoRepository;
import br.com.infnet.repositorys.ParticipanteRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IngressoServiceImpl implements IngressoManager {

	private final IngressoRepository ingressoRepository;
	private final EventoRepository eventoRepository;
	private final ParticipanteRepository participanteRepository;

	@Override
	public void emitirIngresso(Ingresso ingresso) {
		Evento evento = eventoRepository.findById(ingresso.getEvento().getId()).orElseThrow(() -> new NegocioException(TabelaDeErros.REGISTRO_NAO_ENCONTRADO));

		Participante participante = participanteRepository.findById(ingresso.getParticipante().getId())
				.orElseThrow(() -> new NegocioException(TabelaDeErros.REGISTRO_NAO_ENCONTRADO));

		// Verifica se ainda há assentos disponíveis
		if (evento.getQtdeVendida() < evento.getQtdeAssentos()) {
			// Atualiza o evento e o participante no ingresso
			ingresso.setEvento(evento);
			ingresso.setParticipante(participante);

			// Incrementa a quantidade de ingressos vendidos
			evento.venderIngresso();
			eventoRepository.save(evento); // Atualiza o evento no banco de dados

			// Salva o ingresso
			ingressoRepository.save(ingresso);
		} else {
			throw new NegocioException(TabelaDeErros.INGRESSOS_ESGOTADOS); // Altere esta linha conforme necessário
		}
	}

	@Override
	public void atualizarIngresso(Ingresso ingresso) {
		
		if (!ingressoRepository.existsById(ingresso.getId())) {
			throw new NegocioException(TabelaDeErros.REGISTRO_NAO_ENCONTRADO);
		}
		ingressoRepository.save(ingresso);
	}

	@Override
	public void deletarIngresso(Long id) {
		if (!ingressoRepository.existsById(id)) {
			throw new NegocioException(TabelaDeErros.REGISTRO_NAO_ENCONTRADO);
		}
		ingressoRepository.deleteById(id);
	}

	@Override
	public Ingresso buscarIngressoPorId(Long id) {
		return ingressoRepository.findById(id).orElseThrow(() -> new NegocioException(TabelaDeErros.REGISTRO_NAO_ENCONTRADO));
	}

	@Override
	public List<Ingresso> listarIngressos() {
		List<Ingresso> ingressos = ingressoRepository.findAll();
		if (ingressos.isEmpty()) {
			throw new NegocioException(TabelaDeErros.REGISTRO_NAO_ENCONTRADO);
		}
		return ingressos;
	}
}
