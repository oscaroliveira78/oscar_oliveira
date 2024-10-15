package br.com.infnet.services;

import java.util.List;

import org.springframework.stereotype.Service;

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
		
		System.out.println(ingresso);

		Evento evento = eventoRepository.findById(ingresso.getEvento().getId()).orElseThrow(() -> new RuntimeException("Evento não encontrado"));

		Participante participante = participanteRepository.findById(ingresso.getParticipante().getId())
				.orElseThrow(() -> new RuntimeException("Participante não encontrado"));

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
			throw new RuntimeException("Ingressos esgotados para este evento");
		}
	}

	@Override
	public void atualizarIngresso(Ingresso ingresso) {
		
		ingressoRepository.save(ingresso);
	}

	@Override
	public void deletarIngresso(Long id) {
		
		ingressoRepository.deleteById(id);
	}

	@Override
	public Ingresso buscarIngressoPorId(Long id) {
		
		return ingressoRepository.findById(id).orElse(null);
	}

	@Override
	public List<Ingresso> listarIngressos() {
		
		return ingressoRepository.findAll();
	}
}