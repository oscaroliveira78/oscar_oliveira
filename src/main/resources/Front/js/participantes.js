const baseUrl = 'http://localhost:8182/participantes'; // Substitua pela URL correta da sua API

// Função para listar participantes
async function listarParticipantes() {
    const response = await fetch(baseUrl);
    const participantes = await response.json();
    const tbody = document.getElementById('participantes-tbody');
    tbody.innerHTML = ''; // Limpa a tabela antes de preencher

    // Adiciona cada participante à tabela
    participantes.forEach(participante => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${participante.id}</td>
            <td>${participante.nome}</td>
            <td>${participante.email}</td>
            <td>${participante.telefone}</td>
            <td>
                <button class="btn btn-warning btn-sm" onclick="abrirModalAtualizar(${participante.id})">Atualizar</button>
                <button class="btn btn-danger btn-sm" onclick="deletarParticipante(${participante.id})">Deletar</button>
            </td>
        `;
        tbody.appendChild(tr);
    });
}

// Função para registrar um novo participante
async function registrarParticipante() {
    const nome = document.getElementById('nome').value;
    const email = document.getElementById('email').value;
    const telefone = document.getElementById('telefone').value;

    const novoParticipante = {
        nome: nome,
        email: email,
        telefone: telefone
    };

    const response = await fetch(baseUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(novoParticipante)
    });

    if (response.ok) {
        alert('Participante registrado com sucesso!');
        listarParticipantes();
        $('#participanteModal').modal('hide');
    } else {
        alert('Erro ao registrar participante.');
    }
}

// Função para abrir o modal de atualização
async function abrirModalAtualizar(id) {
    const response = await fetch(`${baseUrl}/${id}`);
    const participante = await response.json();

    document.getElementById('atualizar-id').value = participante.id;
    document.getElementById('atualizar-nome').value = participante.nome;
    document.getElementById('atualizar-email').value = participante.email;
    document.getElementById('atualizar-telefone').value = participante.telefone;

    $('#modalAtualizar').modal('show');
}

// Função para atualizar um participante
async function atualizarParticipante() {
    const id = document.getElementById('atualizar-id').value;
    const nome = document.getElementById('atualizar-nome').value;
    const email = document.getElementById('atualizar-email').value;
    const telefone = document.getElementById('atualizar-telefone').value;

    const participanteAtualizado = {
        id: id,
        nome: nome,
        email: email,
        telefone: telefone
    };

    const response = await fetch(`${baseUrl}/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(participanteAtualizado)
    });

    if (response.ok) {
        alert('Participante atualizado com sucesso!');
        listarParticipantes();
        $('#modalAtualizar').modal('hide');
    } else {
        alert('Erro ao atualizar participante.');
    }
}

// Função para deletar um participante
async function deletarParticipante(id) {
    if (confirm('Tem certeza que deseja deletar este participante?')) {
        const response = await fetch(`${baseUrl}/${id}`, {
            method: 'DELETE'
        });

        if (response.ok) {
            alert('Participante deletado com sucesso!');
            listarParticipantes();
        } else {
            alert('Erro ao deletar participante.');
        }
    }
}

// Chama a função de listar participantes ao carregar a página
$(document).ready(function () {
    listarParticipantes();
});