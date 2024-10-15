package br.com.infnet.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import br.com.infnet.models.Evento;
import br.com.infnet.models.Ingresso;
import br.com.infnet.models.Localizacao;
import br.com.infnet.models.Organizador;
import br.com.infnet.models.Participante;
import br.com.infnet.services.EventoServiceImpl;
import br.com.infnet.services.IngressoServiceImpl;
import br.com.infnet.services.LocalizacaoServiceImpl;
import br.com.infnet.services.OrganizadorServiceImpl;
import br.com.infnet.services.ParticipanteServiceImpl;

@Component
@Profile("dev")
public class DataLoader implements CommandLineRunner {

	@Autowired
	private OrganizadorServiceImpl organizadorService;
	@Autowired
	private ParticipanteServiceImpl participanteService;
	@Autowired
	private EventoServiceImpl eventoService;
	@Autowired
	private IngressoServiceImpl ingressoService;
	@Autowired
	private LocalizacaoServiceImpl localizacaoService;

	@Override
	public void run(String... args) throws Exception {
		carregarDados("data/dados_eventos.txt");
	}

	private void carregarDados(String nomeArquivo) {

		try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
			String linha;
			List<Organizador> organizadores = new ArrayList<>();
			List<Participante> participantes = new ArrayList<>();
			List<Localizacao> localizacoes = new ArrayList<>();

			System.out.println("Iniciando o carregamento dos dados do arquivo: " + nomeArquivo);

			while ((linha = reader.readLine()) != null) {
				System.out.println("Processando linha: " + linha);

				String tipoDado = linha.split(":")[0].trim(); // Extrai o tipo de dado

				switch (tipoDado) {
				case "Evento":
					Evento evento = criarEvento(linha, organizadores, localizacoes);
					eventoService.criarEvento(evento);
					System.out.println("Evento criado: " + evento.getNome());
					break;
				case "Organizador":
					Organizador organizador = criarOrganizador(linha);
					organizadorService.criarOrganizador(organizador);
					organizadores.add(organizador);
					System.out.println("Organizador criado: " + organizador.getNome());
					break;
				case "Participante":
					Participante participante = criarParticipante(linha);
					participanteService.registrarParticipante(participante);
					participantes.add(participante);
					System.out.println("Participante criado: " + participante.getNome());
					break;
				case "Localizacao":
					Localizacao localizacao = criarLocalizacao(linha);
					localizacaoService.criarLocalizacao(localizacao);
					localizacoes.add(localizacao);
					System.out.println("Localização criada: " + localizacao.getCidade());
					break;
				case "Ingresso":
					criarIngresso(linha, participantes);
					break;
				default:
					System.err.println("Tipo de dado desconhecido: " + tipoDado);
					break;
				}
			}

			System.out.println("Finalizado o carregamento dos dados.");
		} catch (IOException e) {
			System.err.println("Erro ao carregar o arquivo: " + e.getMessage());
		}
	}

	private Evento criarEvento(String linha, List<Organizador> organizadores, List<Localizacao> localizacoes) {

		System.out.println("Criando evento...");
		String[] partes = linha.split(";");

		String nome = partes[0].split(":")[2].trim();
		String descricao = partes[1].split(":")[1].trim();
		String dataHoraString = partes[2].split(":")[1].trim();

		if (dataHoraString.matches("\\d{2}/\\d{2}/\\d{4} \\d{1,2}")) {
			dataHoraString += ":00";
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		LocalDateTime dataHora;
		try {
			dataHora = LocalDateTime.parse(dataHoraString, formatter);
		} catch (DateTimeParseException e) {
			System.err.println("Erro ao analisar a data e hora: " + e.getMessage());
			return null;
		}

		Integer qtdeAssentos = Integer.parseInt(partes[3].split(":")[1].trim());
		Double preco = Double.parseDouble(partes[4].split(":")[1].trim());
		Long organizadorId = Long.parseLong(partes[5].split(":")[1].trim());
		Long localizacaoId = Long.parseLong(partes[6].split(":")[1].trim());

		Organizador organizador = organizadores.get(Math.toIntExact(organizadorId - 1));
		Localizacao localizacao = localizacoes.get(Math.toIntExact(localizacaoId - 1));

		System.out.println("Evento '" + nome + "' criado com sucesso!");
		return new Evento(nome, descricao, dataHora, qtdeAssentos, preco, organizador, localizacao);
	}

	private Organizador criarOrganizador(String linha) {

		System.out.println("Criando organizador...");
		String[] partes = linha.split(";");
		String nome = partes[0].split(":")[2].trim();
		String contato = partes[1].split(":")[1].trim();
		System.out.println("Organizador '" + nome + "' criado com sucesso!");
		return new Organizador(nome, contato);
	}

	private Participante criarParticipante(String linha) {

		System.out.println("Criando participante...");
		String[] partes = linha.split(";");
		String nome = partes[0].split(":")[2].trim();
		String email = partes[1].split(":")[1].trim();
		String telefone = partes[2].split(":")[1].trim();
		System.out.println("Participante '" + nome + "' criado com sucesso!");
		return new Participante(nome, email, telefone);
	}

	private Localizacao criarLocalizacao(String linha) {

		System.out.println("Criando localização...");
		String[] partes = linha.split(";");
		String endereco = partes[0].split(":")[2].trim();
		String cidade = partes[1].split(":")[1].trim();
		String estado = partes[2].split(":")[1].trim();
		System.out.println("Localização na cidade '" + cidade + "' criada com sucesso!");
		return new Localizacao(endereco, cidade, estado);
	}

	private void criarIngresso(String linha, List<Participante> participantes) {

		System.out.println("Criando ingresso...");
		String[] partes = linha.split(";");

		Long eventoId = Long.parseLong(partes[0].split(":")[2].trim());
		Long participanteId = Long.parseLong(partes[1].split(":")[1].trim());
		boolean pago = Boolean.parseBoolean(partes[2].split(":")[1].trim());

		Evento evento = eventoService.buscarEventoPorId(eventoId);

		if (evento.getQtdeVendida() < evento.getQtdeAssentos()) {
			Participante participante = participantes.get(Math.toIntExact(participanteId - 1));

			evento.venderIngresso();
			eventoService.atualizarEvento(evento);

			Ingresso ingresso = new Ingresso(evento, participante, pago);
			ingressoService.emitirIngresso(ingresso);

			System.out.println("Ingresso criado com sucesso para o evento: " + evento.getNome() + " - Participante: " + participante.getNome());
		} else {
			System.err.println("Ingressos esgotados para o evento: " + evento.getNome());
		}
	}
}
