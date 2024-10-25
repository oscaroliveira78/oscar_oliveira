package br.com.infnet.controllers.openapi;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.infnet.models.Evento;

public interface EventoControllerOpenApi {

//	@Operation(
//			summary = "Consultar de Lista de Contatos por Parâmetros", 
//			description = "Consulta de Lista de Contatos na Base H2 conforme parâmetros passados",
//			responses = {
//					@ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ContatoSaidaDto.class)), examples = {
//							@ExampleObject(name = "Consulta realizada com sucesso") })
//
//					),
//					@ApiResponse(responseCode = "204", content = @Content(schema = @Schema(hidden = true), examples = {
//							@ExampleObject(name = "Consulta realizada com sucesso, não há dados a serem apresentados") })),
//					@ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ErroSaidaDto.class), examples = {
//							@ExampleObject(name = "Erro: Dados de requisição inválidos") })),
//					@ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ErroSaidaDto.class), examples = {
//							@ExampleObject(name = "Erro: Sistêmico") })) }, 
//			parameters = {@Parameter(name = "request-id", explode = Explode.TRUE, schema = @Schema(type = "string"), in = ParameterIn.HEADER, description = "Id de indentificação da request") })
	public ResponseEntity<Evento> criarEvento(@RequestBody Evento evento);

	public ResponseEntity<List<Evento>> listarEventos();

	public ResponseEntity<Evento> buscarEventoPorId(@PathVariable Long id);

	public ResponseEntity<Evento> atualizarEvento(@PathVariable Long id, @RequestBody Evento eventoAtualizado);

	public ResponseEntity<Void> deletarEvento(@PathVariable Long id);

	public ResponseEntity<String> venderIngresso(@PathVariable Long id);

}
