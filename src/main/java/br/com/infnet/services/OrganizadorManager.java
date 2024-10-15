package br.com.infnet.services;

import java.util.List;

import br.com.infnet.models.Organizador;

public interface OrganizadorManager {

	void criarOrganizador(Organizador organizador);

	void atualizarOrganizador(Organizador organizador);

	void deletarOrganizador(Long id);

	Organizador buscarOrganizadorPorId(Long id);

	List<Organizador> listarOrganizadores();
}