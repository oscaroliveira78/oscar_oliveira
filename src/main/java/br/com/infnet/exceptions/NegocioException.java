package br.com.infnet.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class NegocioException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	private final HttpStatus codigoHttp;
	private final String mensagem;
    
    public NegocioException(TabelaDeErros tabela) {
		this.codigoHttp = tabela.getCodigoHttp();
		this.mensagem = tabela.getMensagem();
    }
}