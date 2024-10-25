package br.com.infnet.controllers.openapi;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.infnet.models.Organizador;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface OrganizadorControllerOpenApi {

	@Operation(summary = "Listar organizadores", description = "Retorna uma lista de todos os organizadores.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de organizadores retornada com sucesso.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Organizador.class))),
			@ApiResponse(responseCode = "404", description = "Nenhum organizador encontrado.") })
	public ResponseEntity<List<Organizador>> listarOrganizadores();

	@Operation(summary = "Criar organizador", description = "Cria um novo organizador.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Organizador criado com sucesso.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Organizador.class))),
			@ApiResponse(responseCode = "400", description = "Erro ao criar organizador.") })
	public ResponseEntity<Organizador> criarOrganizador(
			@Parameter(description = "Dados do organizador a ser criado", required = true) @RequestBody Organizador organizador);

	@Operation(summary = "Atualizar organizador", description = "Atualiza um organizador existente.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Organizador atualizado com sucesso.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Organizador.class))),
			@ApiResponse(responseCode = "404", description = "Organizador não encontrado.") })
	public ResponseEntity<Organizador> atualizarOrganizador(
			@Parameter(description = "ID do organizador a ser atualizado", required = true) @PathVariable Long id,
			@Parameter(description = "Dados do organizador a serem atualizados", required = true) @RequestBody Organizador organizador);

	@Operation(summary = "Deletar organizador", description = "Deleta um organizador pelo ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Organizador deletado com sucesso."),
			@ApiResponse(responseCode = "404", description = "Organizador não encontrado.") })
	public ResponseEntity<Void> deletarOrganizador(@Parameter(description = "ID do organizador a ser deletado", required = true) @PathVariable Long id);

	@Operation(summary = "Buscar organizador por ID", description = "Retorna um organizador específico pelo ID.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Organizador encontrado.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Organizador.class))),
			@ApiResponse(responseCode = "404", description = "Organizador não encontrado.") })
	public ResponseEntity<Organizador> buscarOrganizadorPorId(
			@Parameter(description = "ID do organizador a ser buscado", required = true) @PathVariable Long id);
}