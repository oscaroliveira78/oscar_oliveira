package br.com.infnet.controllers.openapi;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.infnet.models.Organizador;

public interface OrganizadorControllerOpenApi {

	public List<Organizador> listarOrganizadores();

	public ResponseEntity<Organizador> criarOrganizador(@RequestBody Organizador organizador);

	public ResponseEntity<Organizador> atualizarOrganizador(@PathVariable Long id, @RequestBody Organizador organizador);

	public ResponseEntity<Void> deletarOrganizador(@PathVariable Long id);

	public ResponseEntity<Organizador> buscarOrganizadorPorId(@PathVariable Long id);
}
