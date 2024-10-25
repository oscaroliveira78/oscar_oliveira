package br.com.infnet.controllers.openapi;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.infnet.models.Localizacao;
import br.com.infnet.models.dtos.CepResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface LocalizacaoControllerOpenApi {

    @Operation(summary = "Listar localizações", description = "Retorna uma lista de todas as localizações.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de localizações retornada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhuma localização encontrada.")
    })
	public List<Localizacao> listarLocalizacoes();

    @Operation(summary = "Criar localização", description = "Cria uma nova localização.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Localização criada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro ao criar localização.")
    })
	public ResponseEntity<Localizacao> criarLocalizacao(@RequestBody Localizacao localizacao);

    @Operation(summary = "Atualizar localização", description = "Atualiza uma localização existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Localização atualizada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Localização não encontrada.")
    })
	public ResponseEntity<Localizacao> atualizarLocalizacao(@PathVariable Long id, @RequestBody Localizacao localizacao);

    @Operation(summary = "Deletar localização", description = "Deleta uma localização pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Localização deletada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Localização não encontrada.")
    })
	public ResponseEntity<String> deletarLocalizacao(@PathVariable Long id);

    @Operation(summary = "Buscar localização por ID", description = "Retorna uma localização específica pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Localização encontrada."),
            @ApiResponse(responseCode = "404", description = "Localização não encontrada.")
    })
	public ResponseEntity<Localizacao> buscarLocalizacaoPorId(@PathVariable Long id);

    @Operation(summary = "Buscar endereço por CEP", description = "Retorna os detalhes do endereço a partir do CEP.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço encontrado."),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado.")
    })
	public ResponseEntity<CepResponseDTO> buscarCep(@PathVariable String cep) throws InterruptedException;
}
