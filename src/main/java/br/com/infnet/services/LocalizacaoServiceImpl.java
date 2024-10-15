package br.com.infnet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infnet.models.Localizacao;
import br.com.infnet.repositorys.LocalizacaoRepository;

@Service
public class LocalizacaoServiceImpl {

    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    public void criarLocalizacao(Localizacao localizacao) {
        localizacaoRepository.save(localizacao);
    }

    public List<Localizacao> listarLocalizacoes() {
        return localizacaoRepository.findAll();
    }

    public void atualizarLocalizacao(Localizacao localizacao) {
        localizacaoRepository.save(localizacao);
    }

    public void deletarLocalizacao(Long id) {
        localizacaoRepository.deleteById(id);
    }

    public Localizacao buscarLocalizacaoPorId(Long id) {
        return localizacaoRepository.findById(id).orElse(null);
    }
}