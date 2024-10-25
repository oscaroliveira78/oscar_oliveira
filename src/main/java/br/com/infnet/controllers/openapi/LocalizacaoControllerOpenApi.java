package br.com.infnet.controllers.openapi;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.infnet.models.Localizacao;
import br.com.infnet.models.dtos.CepResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface LocalizacaoControllerOpenApi {

	@Operation(summary = "Listar localizações", description = "Retorna uma lista de todas as localizações.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de localizações retornada com sucesso.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Localizacao.class))),
			@ApiResponse(responseCode = "404", description = "Nenhuma localização encontrada.") })
	public ResponseEntity<List<Localizacao>> listarLocalizacoes();

	@Operation(summary = "Criar localização", description = "Cria uma nova localização.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Localização criada com sucesso.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Localizacao.class))),
			@ApiResponse(responseCode = "400", description = "Erro ao criar localização.") })

	public ResponseEntity<Localizacao> criarLocalizacao(
			@Parameter(description = "Dados da localização a ser criada", required = true) @RequestBody Localizacao localizacao);

	@Operation(summary = "Atualizar localização", description = "Atualiza uma localização existente.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Localização atualizada com sucesso.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Localizacao.class))),
			@ApiResponse(responseCode = "404", description = "Localização não encontrada.") })

	public ResponseEntity<Localizacao> atualizarLocalizacao(
			@Parameter(description = "ID da localização a ser atualizada", required = true) @PathVariable Long id,
			@Parameter(description = "Dados da localização a serem atualizados", required = true) @RequestBody Localizacao localizacao);

	@Operation(summary = "Deletar localização", description = "Deleta uma localização pelo ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Localização deletada com sucesso."),
			@ApiResponse(responseCode = "404", description = "Localização não encontrada.") })

	public ResponseEntity<String> deletarLocalizacao(@Parameter(description = "ID da localização a ser deletada", required = true) @PathVariable Long id);

	@Operation(summary = "Buscar localização por ID", description = "Retorna uma localização específica pelo ID.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Localização encontrada.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Localizacao.class))),
			@ApiResponse(responseCode = "404", description = "Localização não encontrada.") })
	public ResponseEntity<Localizacao> buscarLocalizacaoPorId(
			@Parameter(description = "ID da localização a ser buscada", required = true) @PathVariable Long id);

	@Operation(summary = "Buscar endereço por CEP", description = "Retorna os detalhes do endereço a partir do CEP.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Endereço encontrado.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CepResponseDTO.class))),
			@ApiResponse(responseCode = "404", description = "Endereço não encontrado.") })

	public ResponseEntity<CepResponseDTO> buscarCep(@Parameter(description = "CEP a ser buscado", required = true) @PathVariable String cep)
			throws InterruptedException;
}
