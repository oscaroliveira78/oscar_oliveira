package br.com.infnet.exceptions;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TabelaDeErros {

	VALIDACAO(HttpStatus.BAD_REQUEST, "Dados de requisição inválidos"),

	REGISTRO_NAO_ENCONTRADO(HttpStatus.NOT_FOUND, "Dado(s) não encontrado(s)"),

	INGRESSOS_ESGOTADOS(HttpStatus.CONFLICT, "Ingressos Esgotados."),

	SISTEMA(HttpStatus.INTERNAL_SERVER_ERROR, "sitema indisponível");

	private final HttpStatus codigoHttp;
	private final String mensagem;
}
