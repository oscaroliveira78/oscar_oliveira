package br.com.infnet.controllers.openapi;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.infnet.models.Ingresso;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface IngressoControllerOpenApi {

    @Operation(summary = "Emitir ingresso", description = "Emite um novo ingresso para um evento.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ingresso emitido com sucesso."),
            @ApiResponse(responseCode = "404", description = "Evento ou participante não encontrado."),
            @ApiResponse(responseCode = "400", description = "Ingressos esgotados ou erro de negócios.")
    })
	public ResponseEntity<Ingresso> emitirIngresso(@RequestBody Ingresso ingresso);

    @Operation(summary = "Listar ingressos", description = "Retorna uma lista de todos os ingressos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de ingressos retornada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum ingresso encontrado.")
    })
	public ResponseEntity<List<Ingresso>> listarIngressos();

    @Operation(summary = "Buscar ingresso por ID", description = "Retorna um ingresso específico pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ingresso encontrado."),
            @ApiResponse(responseCode = "404", description = "Ingresso não encontrado.")
    })
	public ResponseEntity<Ingresso> buscarIngressoPorId(@PathVariable Long id);

    @Operation(summary = "Atualizar ingresso", description = "Atualiza as informações de um ingresso existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ingresso atualizado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Ingresso não encontrado.")
    })
	public ResponseEntity<Ingresso> atualizarIngresso(@PathVariable Long id, @RequestBody Ingresso ingresso);

    @Operation(summary = "Deletar ingresso", description = "Deleta um ingresso pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ingresso deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Ingresso não encontrado.")
    })
    @DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarIngresso(@PathVariable Long id);

}
