package br.com.infnet.services;

import java.util.List;

import br.com.infnet.models.Ingresso;

public interface IngressoManager {

	void emitirIngresso(Ingresso ingresso);

	void atualizarIngresso(Ingresso ingresso);

	void deletarIngresso(Long id);

	Ingresso buscarIngressoPorId(Long id);

	List<Ingresso> listarIngressos();
}