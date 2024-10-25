package br.com.infnet.controllers.openapi;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.infnet.models.Ingresso;

public interface IngressoControllerOpenApi {

	public ResponseEntity<Ingresso> emitirIngresso(@RequestBody Ingresso ingresso);

	public ResponseEntity<List<Ingresso>> listarIngressos();

	public ResponseEntity<Ingresso> buscarIngressoPorId(@PathVariable Long id);

	public ResponseEntity<Ingresso> atualizarIngresso(@PathVariable Long id, @RequestBody Ingresso ingresso);

	public ResponseEntity<Void> deletarIngresso(@PathVariable Long id);

}
