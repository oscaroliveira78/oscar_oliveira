package br.com.infnet.services;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.infnet.exceptions.NegocioException;
import br.com.infnet.exceptions.TabelaDeErros;
import br.com.infnet.models.Evento;
import br.com.infnet.repositorys.EventoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventoServiceImpl implements EventoManager {
	private final EventoRepository eventoRepository;

	@Override
	public void criarEvento(Evento evento) {
		evento.setQtdeVendida(0);
		eventoRepository.save(evento);
	}

	@Override
	public void atualizarEvento(Evento evento) {
		if (!eventoRepository.existsById(evento.getId())) {
			throw new NegocioException(TabelaDeErros.REGISTRO_NAO_ENCONTRADO);
		}
		eventoRepository.save(evento);
	}

	@Override
	public void deletarEvento(Long id) {
		if (!eventoRepository.existsById(id)) {
			throw new NegocioException(TabelaDeErros.REGISTRO_NAO_ENCONTRADO);
		}
		eventoRepository.deleteById(id);
	}

	@Override
	public Evento buscarEventoPorId(Long id) {
		return eventoRepository.findById(id).orElseThrow(() -> new NegocioException(TabelaDeErros.REGISTRO_NAO_ENCONTRADO));
	}

	@Override
	public List<Evento> listarEventos() {
		List<Evento> eventos = eventoRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
		if (eventos.isEmpty()) {
			throw new NegocioException(TabelaDeErros.REGISTRO_NAO_ENCONTRADO);
		}
		return eventos;
	}

	@Override
	public void venderIngresso(Long eventoId) {
		Evento evento = buscarEventoPorId(eventoId);
		evento.venderIngresso();
		atualizarEvento(evento);
	}
}