package br.com.infnet.controllers.openapi;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.infnet.models.Evento;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface EventoControllerOpenApi {

	@Operation(summary = "Cria um novo evento", description = "Cria um evento com os dados fornecidos.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Evento criado com sucesso."),
	        @ApiResponse(responseCode = "400", description = "Dados inválidos.",
	            content = @Content(
	                mediaType = "application/json",
	                examples = @ExampleObject(value = "{\n" +
	                    "  \"nome\": \"Nome é obrigatório.\",\n" +
	                    "  \"descricao\": \"Descrição não pode ser vazia.\",\n" +
	                    "  \"dataHora\": \"Data e hora são obrigatórias.\",\n" +
	                    "  \"qtdeAssentos\": \"Quantidade de assentos deve ser pelo menos 1.\",\n" +
	                    "  \"qtdeVendida\": \"Quantidade vendida não pode ser negativa.\",\n" +
	                    "  \"preco\": \"Preço não pode ser negativo.\"\n" +
	                    "}")
	            )
	        )
	})
	public ResponseEntity<Evento> criarEvento(@RequestBody Evento evento);

    @Operation(summary = "Lista todos os eventos", description = "Retorna uma lista de todos os eventos cadastrados.")
    @ApiResponse(responseCode = "200", description = "Lista de eventos retornada com sucesso.")
	public ResponseEntity<List<Evento>> listarEventos();

    @Operation(summary = "Busca um evento pelo ID", description = "Retorna os detalhes de um evento específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evento encontrado."),
            @ApiResponse(responseCode = "404", description = "Evento não encontrado.")
    })
	public ResponseEntity<Evento> buscarEventoPorId(@PathVariable Long id);

    @Operation(summary = "Atualiza um evento existente", description = "Atualiza os detalhes de um evento específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evento atualizado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Evento não encontrado.")
    })
	public ResponseEntity<Evento> atualizarEvento(@PathVariable Long id, @RequestBody Evento eventoAtualizado);

    @Operation(summary = "Deleta um evento", description = "Remove um evento existente pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Evento deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Evento não encontrado.")
    })
	public ResponseEntity<Void> deletarEvento(@PathVariable Long id);

    @Operation(summary = "Vende um ingresso para um evento", description = "Registra a venda de um ingresso para um evento específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ingresso vendido com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro ao vender ingresso.")
    })
	public ResponseEntity<String> venderIngresso(@PathVariable Long id);

}
