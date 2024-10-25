package br.com.infnet.controllers.openapi;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.infnet.models.Localizacao;
import br.com.infnet.models.dtos.CepResponseDTO;

public interface LocalizacaoControllerOpenApi {

	public List<Localizacao> listarLocalizacoes();

	public ResponseEntity<Localizacao> criarLocalizacao(@RequestBody Localizacao localizacao);

	public ResponseEntity<Localizacao> atualizarLocalizacao(@PathVariable Long id, @RequestBody Localizacao localizacao);

	public ResponseEntity<String> deletarLocalizacao(@PathVariable Long id);

	public ResponseEntity<Localizacao> buscarLocalizacaoPorId(@PathVariable Long id);

	public ResponseEntity<CepResponseDTO> buscarCep(@PathVariable String cep) throws InterruptedException;
}
