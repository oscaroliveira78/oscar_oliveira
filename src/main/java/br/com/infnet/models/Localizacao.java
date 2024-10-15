package br.com.infnet.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Localizacao {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
	@SequenceGenerator(name = "id_generator", sequenceName = "global_sequence", allocationSize = 1)
	private Long id;

	private String endereco;
	private String cidade;
	private String estado;

	public Localizacao(String endereco, String cidade, String estado) {
		this.endereco = endereco;
		this.cidade = cidade;
		this.estado = estado;
	}

}