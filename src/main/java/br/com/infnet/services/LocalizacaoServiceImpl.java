package br.com.infnet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.infnet.exceptions.NegocioException;
import br.com.infnet.exceptions.TabelaDeErros;
import br.com.infnet.models.Localizacao;
import br.com.infnet.repositorys.LocalizacaoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocalizacaoServiceImpl {

    private final LocalizacaoRepository localizacaoRepository;

    public void criarLocalizacao(Localizacao localizacao) {
        localizacaoRepository.save(localizacao);
    }

    public List<Localizacao> listarLocalizacoes() {
        List<Localizacao> localizacoes = localizacaoRepository.findAll(Sort.by(Sort.Direction.ASC, "endereco"));
        if (localizacoes.isEmpty()) {
            throw new NegocioException(TabelaDeErros.REGISTRO_NAO_ENCONTRADO);
        }
        return localizacoes;
    }

    public void atualizarLocalizacao(Localizacao localizacao) {
        if (!localizacaoRepository.existsById(localizacao.getId())) {
            throw new NegocioException(TabelaDeErros.REGISTRO_NAO_ENCONTRADO);
        }
        localizacaoRepository.save(localizacao);
    }

    public void deletarLocalizacao(Long id) {
        if (!localizacaoRepository.existsById(id)) {
            throw new NegocioException(TabelaDeErros.REGISTRO_NAO_ENCONTRADO);
        }
        localizacaoRepository.deleteById(id);
    }

    public Localizacao buscarLocalizacaoPorId(Long id) {
        return localizacaoRepository.findById(id)
                .orElseThrow(() -> new NegocioException(TabelaDeErros.REGISTRO_NAO_ENCONTRADO));
    }
}
