package br.com.infnet.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Organizador {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
	@SequenceGenerator(name = "id_generator", sequenceName = "global_sequence", allocationSize = 1)
	private Long id;

	@NotBlank(message = "O nome é obrigatório.")
	private String nome;

	@NotBlank(message = "O contato é obrigatório.")
	private String contato;

	public Organizador(String nome, String contato) {
		this.nome = nome;
		this.contato = contato;
	}
}