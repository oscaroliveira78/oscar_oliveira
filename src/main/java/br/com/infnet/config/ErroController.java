package br.com.infnet.config;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErroController {

//	@ExceptionHandler(NegocioException.class)
//	public ResponseEntity<ErroSaidaDto> handle(NegocioException e) {
//		return ResponseEntity.status(e.getCodigoHttp()).body(new ErroSaidaDto(e.getMensagem()));
//	}

}
