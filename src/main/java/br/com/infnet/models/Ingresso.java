package br.com.infnet.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.AssertTrue;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Ingresso {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
	@SequenceGenerator(name = "id_generator", sequenceName = "global_sequence", allocationSize = 1)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "evento_id", nullable = false)
	private Evento evento;

	@ManyToOne
	@JoinColumn(name = "participante_id", nullable = false)
	private Participante participante;

    @AssertTrue(message = "O ingresso deve estar marcado como pago.")
    private boolean pago = false; // Define o valor padrão como false

	public Ingresso(Evento evento, Participante participante, boolean pago) {
		this.evento = evento;
		this.participante = participante;
		this.pago = pago;
	}
}