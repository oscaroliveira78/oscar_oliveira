package br.com.infnet.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.infnet.models.Evento;
import br.com.infnet.repositorys.EventoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventoServiceImpl implements EventoManager {
	private final EventoRepository eventoRepository;

	public void criarEvento(Evento evento) {
		
		evento.setQtdeVendida(0);
		eventoRepository.save(evento);
	}

	@Override
	public void atualizarEvento(Evento evento) {
		
		eventoRepository.save(evento);
	}

	@Override
	public void deletarEvento(Long id) {
		
		eventoRepository.deleteById(id);
	}

	@Override
	public Evento buscarEventoPorId(Long id) {
		
		return eventoRepository.findById(id).orElse(null);
	}

	@Override
	public List<Evento> listarEventos() {
		
		return eventoRepository.findAll();
	}

	@Override
	public void venderIngresso(Long eventoId) {
		
		Evento evento = buscarEventoPorId(eventoId);
		if (evento != null) {
			evento.venderIngresso();
			atualizarEvento(evento);
		} else {
			throw new RuntimeException("Evento não encontrado.");
		}
	}
}