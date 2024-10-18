package br.com.infnet.controllers;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

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

import br.com.infnet.infra.ApiClientViaCep;
import br.com.infnet.models.Localizacao;
import br.com.infnet.models.dtos.CepResponseDTO;
import br.com.infnet.services.LocalizacaoServiceImpl;

@RestController
@RequestMapping("/localizacoes")
@CrossOrigin
public class LocalizacaoController {

	private LocalizacaoServiceImpl localizacaoService;
	private ApiClientViaCep apiClientViaCep;

	public LocalizacaoController(LocalizacaoServiceImpl localizacaoService, ApiClientViaCep apiClientViaCep) {
		this.localizacaoService = localizacaoService;
		this.apiClientViaCep = apiClientViaCep;
	}

	@GetMapping
	public List<Localizacao> listarLocalizacoes() {

		return localizacaoService.listarLocalizacoes();
	}

	@PostMapping
	public ResponseEntity<Localizacao> criarLocalizacao(@RequestBody Localizacao localizacao) {

		localizacaoService.criarLocalizacao(localizacao);
		return ResponseEntity.ok(localizacao);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Localizacao> atualizarLocalizacao(@PathVariable Long id, @RequestBody Localizacao localizacao) {

		localizacao.setId(id);
		localizacaoService.atualizarLocalizacao(localizacao);
		return ResponseEntity.ok(localizacao);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletarLocalizacao(@PathVariable Long id) {

		try {
			localizacaoService.deletarLocalizacao(id);
		} catch (SQLIntegrityConstraintViolationException e) {

			return ResponseEntity.badRequest().body("Existem eventos vinculados a esta Localização, não será possível continuar!!!");
		}
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Localizacao> buscarLocalizacaoPorId(@PathVariable Long id) {

		Localizacao localizacao = localizacaoService.buscarLocalizacaoPorId(id);
		return localizacao != null ? ResponseEntity.ok(localizacao) : ResponseEntity.notFound().build();
	}

	@GetMapping("/cep/{cep}")
	public ResponseEntity<CepResponseDTO> buscarCep(@PathVariable String cep) throws InterruptedException {

		Thread.sleep(3000); // Somente para mostrar o Loader no Front

		CepResponseDTO endereco = apiClientViaCep.findByCep(cep);
		System.out.println(endereco);
		return endereco != null ? ResponseEntity.ok(endereco) : ResponseEntity.notFound().build();
	}
}
