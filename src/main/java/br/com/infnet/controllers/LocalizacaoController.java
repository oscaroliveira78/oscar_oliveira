package br.com.infnet.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.infnet.controllers.openapi.LocalizacaoControllerOpenApi;
import br.com.infnet.infra.ApiClientViaCep;
import br.com.infnet.models.Localizacao;
import br.com.infnet.models.dtos.CepResponseDTO;
import br.com.infnet.services.LocalizacaoServiceImpl;

@RestController
@RequestMapping("/localizacoes")
@CrossOrigin
public class LocalizacaoController implements LocalizacaoControllerOpenApi {

	private LocalizacaoServiceImpl localizacaoService;
	private ApiClientViaCep apiClientViaCep;

	public LocalizacaoController(LocalizacaoServiceImpl localizacaoService, ApiClientViaCep apiClientViaCep) {
		this.localizacaoService = localizacaoService;
		this.apiClientViaCep = apiClientViaCep;
	}

	@Override
	@GetMapping
	public ResponseEntity<List<Localizacao>> listarLocalizacoes() {

		return ResponseEntity.ok(localizacaoService.listarLocalizacoes());
	}

	@Override
	@PostMapping
	public ResponseEntity<Localizacao> criarLocalizacao(@Valid @RequestBody Localizacao localizacao) {

		localizacaoService.criarLocalizacao(localizacao);
		return ResponseEntity.ok(localizacao);
	}

	@Override
	@PutMapping("/{id}")
	public ResponseEntity<Localizacao> atualizarLocalizacao(@PathVariable Long id, @Valid @RequestBody Localizacao localizacao) {

		localizacao.setId(id);
		localizacaoService.atualizarLocalizacao(localizacao);
		return ResponseEntity.ok(localizacao);
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletarLocalizacao(@PathVariable Long id) {

		localizacaoService.deletarLocalizacao(id);

		return ResponseEntity.noContent().build();
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<Localizacao> buscarLocalizacaoPorId(@PathVariable Long id) {

		Localizacao localizacao = localizacaoService.buscarLocalizacaoPorId(id);
		return localizacao != null ? ResponseEntity.ok(localizacao) : ResponseEntity.notFound().build();
	}

	@Override
	@GetMapping("/cep/{cep}")
	public ResponseEntity<CepResponseDTO> buscarCep(@PathVariable String cep) throws InterruptedException {

		Thread.sleep(3000); // Somente para mostrar o Loader no Front

		CepResponseDTO endereco = apiClientViaCep.findByCep(cep);
		System.out.println(endereco);
		return endereco != null ? ResponseEntity.ok(endereco) : ResponseEntity.notFound().build();
	}
}
