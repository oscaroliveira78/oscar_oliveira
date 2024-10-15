package br.com.infnet.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.infnet.models.Localizacao;

@Service
public interface LocalizacaoManager {

	public void criarLocalizacao(Localizacao localizacao);

	public List<Localizacao> listarLocalizacoes();

	public void atualizarLocalizacao(Localizacao localizacao);

	public void deletarLocalizacao(Long id);

	public Localizacao buscarLocalizacaoPorId(Long id);
}