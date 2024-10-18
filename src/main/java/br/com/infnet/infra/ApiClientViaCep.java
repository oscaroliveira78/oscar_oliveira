package br.com.infnet.infra;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.infnet.models.dtos.CepResponseDTO;

@FeignClient(name = "apiViaCep", url = "https://viacep.com.br/ws")
public interface ApiClientViaCep {

	@GetMapping("/{cep}/json/")
	CepResponseDTO findByCep(@PathVariable("cep") String cep);
}