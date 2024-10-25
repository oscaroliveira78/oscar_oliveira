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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

    @NotNull(message = "Nome é obrigatório.")
    @NotEmpty(message = "Nome não pode ser vazio.")
    private String nome;

    @NotNull(message = "Descrição é obrigatória.")
    @NotEmpty(message = "Descrição não pode ser vazia.")
    private String descricao;

    @NotNull(message = "Data e hora são obrigatórias.")
    private LocalDateTime dataHora;

    @NotNull(message = "Quantidade de assentos é obrigatória.")
    @Min(value = 1, message = "Quantidade de assentos deve ser pelo menos 1.")
    private Integer qtdeAssentos;

    @NotNull(message = "Quantidade vendida é obrigatória.")
    @Min(value = 0, message = "Quantidade vendida não pode ser negativa.")
    private Integer qtdeVendida = 0; // Valor padrão de 0

    @NotNull(message = "Preço é obrigatório.")
    @Min(value = 0, message = "Preço não pode ser negativo.")
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