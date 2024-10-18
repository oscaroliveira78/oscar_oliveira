package br.com.infnet.models.dtos;

public class CepResponseDTO {

	private String cep;
	private String logradouro;
	private String unidade;
	private String bairro;
	private String uf;
	private String estado;

	// Getters e Setters
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "CepResponseDTO [cep=" + cep + ", logradouro=" + logradouro + ", unidade=" + unidade + ", bairro=" + bairro + ", uf=" + uf + ", estado=" + estado
				+ "]";
	}

}
