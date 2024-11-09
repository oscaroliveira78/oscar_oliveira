package br.com.infnet.services;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.infnet.exceptions.NegocioException;
import br.com.infnet.exceptions.TabelaDeErros;
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
		if (!organizadorRepository.existsById(organizador.getId())) {
			throw new NegocioException(TabelaDeErros.REGISTRO_NAO_ENCONTRADO);
		}
		organizadorRepository.save(organizador);
	}

	@Override
	public void deletarOrganizador(Long id) {
		if (!organizadorRepository.existsById(id)) {
			throw new NegocioException(TabelaDeErros.REGISTRO_NAO_ENCONTRADO);
		}
		organizadorRepository.deleteById(id);
	}

	@Override
	public Organizador buscarOrganizadorPorId(Long id) {
		return organizadorRepository.findById(id).orElseThrow(() -> new NegocioException(TabelaDeErros.REGISTRO_NAO_ENCONTRADO));
	}

	@Override
	public List<Organizador> listarOrganizadores() {
		List<Organizador> organizadores = organizadorRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
		if (organizadores.isEmpty()) {
			throw new NegocioException(TabelaDeErros.REGISTRO_NAO_ENCONTRADO);
		}
		return organizadores;
	}
}
