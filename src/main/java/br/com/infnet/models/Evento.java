package br.com.infnet.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Evento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private String descricao;
	private LocalDateTime dataHora;
	private Integer qtdeAssentos;
	private Integer qtdeVendida;
	private Double preco;

	@ManyToOne
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
	@SequenceGenerator(name = "id_generator", sequenceName = "global_sequence", allocationSize = 1)
	private Organizador organizador;

	@ManyToOne
	@JoinColumn(name = "localizacao_id", nullable = false) // Relação obrigatória
	private Localizacao localizacao;

	@OneToMany(mappedBy = "evento")
	private List<Ingresso> ingressos;

	public Evento(String nome, String descricao, LocalDateTime dataHora, Integer qtdeAssentos, Double preco, Organizador organizador, Localizacao localizacao) {
		this.nome = nome;
		this.descricao = descricao;
		this.dataHora = dataHora;
		this.qtdeAssentos = qtdeAssentos;
		this.qtdeVendida = 0;
		this.preco = preco;
		this.organizador = organizador;
		this.localizacao = localizacao;
	}

	public void venderIngresso() {
		if (qtdeVendida < qtdeAssentos) {
			qtdeVendida++;
		} else {
			throw new RuntimeException("Ingressos esgotados.");
		}
	}

}