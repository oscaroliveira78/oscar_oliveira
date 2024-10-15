package br.com.infnet.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.infnet.models.Organizador;
import br.com.infnet.repositorys.OrganizadorRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrganizadorServiceImpl implements OrganizadorManager {
	private final OrganizadorRepository organizadorRepository;

	@Override
	public void criarOrganizador(Organizador organizador) {
		organizadorRepository.save(organizador);
	}

	@Override
	public void atualizarOrganizador(Organizador organizador) {
		organizadorRepository.save(organizador);
	}

	@Override
	public void deletarOrganizador(Long id) {
		organizadorRepository.deleteById(id);
	}

	@Override
	public Organizador buscarOrganizadorPorId(Long id) {
		return organizadorRepository.findById(id).orElse(null);
	}

	@Override
	public List<Organizador> listarOrganizadores() {
		return organizadorRepository.findAll();
	}
}