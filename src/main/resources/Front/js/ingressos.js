
const baseUrl = 'http://localhost:8182/ingressos';
const eventosUrl = 'http://localhost:8182/eventos'; // URL para obter eventos
const participantesUrl = 'http://localhost:8182/participantes'; // URL para obter participantes

async function listarEventos() {

    const response = await fetch(eventosUrl);
    const eventos = await response.json();
    const tbody = document.getElementById('ingressos-tbody');
    tbody.innerHTML = ''; // Limpa o conteúdo anterior da tabela

    console.log(eventos);

    eventos.forEach(evento => {
        // Verifica se o evento possui ingressos
        if (evento.ingressos && evento.ingressos.length > 0) {
            evento.ingressos.forEach(ingresso => {
                const tr = document.createElement('tr');

                tr.innerHTML = `
                    <td>${ingresso.id}</td>
                    <td>${evento.nome}</td> <!-- Nome do evento -->
                    <td>${ingresso.participante.nome}</td>
                    <td>${ingresso.pago ? 'Sim' : 'Não'}</td>
                    <td>
                        <button class="btn btn-warning btn-sm" onclick="abrirModalAtualizar(${ingresso.id})">Atualizar</button>
                        <button class="btn btn-danger btn-sm" onclick="deletarIngresso(${ingresso.id})">Deletar</button>
                    </td>
                `;

                tbody.appendChild(tr);
            });
        } else {
            // Caso o evento não tenha ingressos, exiba uma mensagem na tabela
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td colspan="5">Nenhum ingresso disponível para o evento ${evento.nome}</td>
            `;
            tbody.appendChild(tr);
        }
    });
}

async function carregarEventos() {
    const response = await fetch(eventosUrl);
    const eventos = await response.json();
    const eventoSelect = document.getElementById('evento-id');
    eventoSelect.innerHTML = '';

    eventos.forEach(evento => {
        const option = document.createElement('option');
        option.value = evento.id;
        option.textContent = `${evento.nome} (R$ ${evento.preco.toFixed(2)} - ${evento.qtdeAssentos - evento.qtdeVendida} assentos disponíveis)`;
        eventoSelect.appendChild(option);
    });
}

async function carregarParticipantes() {
    const response = await fetch(participantesUrl);
    const participantes = await response.json();
    const participanteSelect = document.getElementById('participante-id');
    participanteSelect.innerHTML = '';

    participantes.forEach(participante => {
        const option = document.createElement('option');
        option.value = participante.id;
        option.textContent = participante.nome;
        participanteSelect.appendChild(option);
    });
}

async function emitirIngresso() {

    const eventoId = document.getElementById('evento-id').value;
    const participanteId = document.getElementById('participante-id').value;
    const pago = document.getElementById('pago').checked;

    const novoIngresso = {
        evento: { id: eventoId }, // Passando apenas o ID do evento
        participante: { id: participanteId }, // Passando apenas o ID do participante
        pago: pago
    };

    const response = await fetch(baseUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(novoIngresso)
    });

    if (response.ok) {
        alert('Ingresso emitido com sucesso!');
        listarIngressos();
        $('#ingressoModal').modal('hide');
    } else {
        alert('Erro ao emitir ingresso.');
    }
}

$(document).ready(function () {
    carregarEventos(); // Carrega os eventos ao iniciar
    carregarParticipantes(); // Carrega os participantes ao iniciar
    listarEventos(); // Carrega os eventos ao iniciar
});