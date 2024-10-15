package br.com.infnet.services;

import java.util.List;

import br.com.infnet.models.Evento;

public interface EventoManager {

	void criarEvento(Evento evento);

	void atualizarEvento(Evento evento);

	void deletarEvento(Long id);

	Evento buscarEventoPorId(Long id);

	List<Evento> listarEventos();

	void venderIngresso(Long eventoId);
}