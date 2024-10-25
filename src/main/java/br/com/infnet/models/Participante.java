package br.com.infnet.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Participante {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
	@SequenceGenerator(name = "id_generator", sequenceName = "global_sequence", allocationSize = 1)
	private Long id;

	@NotBlank(message = "O nome é obrigatório.")
	private String nome;

	@NotBlank(message = "O email é obrigatório.")
	@Email(message = "O email deve ser válido.")
	private String email;

	@NotBlank(message = "O telefone é obrigatório.")
	private String telefone;

	public Participante(String nome, String email, String telefone) {
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
	}

}