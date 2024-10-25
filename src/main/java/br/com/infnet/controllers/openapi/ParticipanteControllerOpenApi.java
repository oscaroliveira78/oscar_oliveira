package br.com.infnet.controllers.openapi;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.infnet.models.Participante;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface ParticipanteControllerOpenApi {

    @Operation(summary = "Listar participantes", description = "Retorna uma lista de todos os participantes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de participantes retornada com sucesso.",
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = Participante.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum participante encontrado.")
    })
    public ResponseEntity<List<Participante>> listarParticipantes();

    @Operation(summary = "Registrar participante", description = "Registra um novo participante.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Participante registrado com sucesso.",
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = Participante.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao registrar participante.")
    })
    public ResponseEntity<Participante> registrarParticipante(
        @Parameter(description = "Dados do participante a ser registrado", required = true) 
        @RequestBody Participante participante);

    @Operation(summary = "Atualizar participante", description = "Atualiza um participante existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Participante atualizado com sucesso.",
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = Participante.class))),
            @ApiResponse(responseCode = "404", description = "Participante não encontrado.")
    })
    public ResponseEntity<Participante> atualizarParticipante(
        @Parameter(description = "ID do participante a ser atualizado", required = true) 
        @PathVariable Long id,
        @Parameter(description = "Dados do participante a serem atualizados", required = true) 
        @RequestBody Participante participante);

    @Operation(summary = "Deletar participante", description = "Deleta um participante pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Participante deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Participante não encontrado.")
    })
    public ResponseEntity<Void> deletarParticipante(
        @Parameter(description = "ID do participante a ser deletado", required = true) 
        @PathVariable Long id);

    @Operation(summary = "Buscar participante por ID", description = "Retorna um participante específico pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Participante encontrado.",
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = Participante.class))),
            @ApiResponse(responseCode = "404", description = "Participante não encontrado.")
    })
    public ResponseEntity<Participante> buscarParticipantePorId(
        @Parameter(description = "ID do participante a ser buscado", required = true) 
        @PathVariable Long id);
}
