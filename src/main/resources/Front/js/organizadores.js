const baseUrl = 'http://localhost:8182/organizadores'; // Substitua pela URL correta da sua API

async function listarOrganizadores() {
    const response = await fetch(baseUrl);
    const organizadores = await response.json();
    const tbody = document.getElementById('organizadores-tbody');
    tbody.innerHTML = '';

    organizadores.forEach(organizador => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${organizador.id}</td>
            <td>${organizador.nome}</td>
            <td>${organizador.contato}</td>
            <td>
                <button class="btn btn-warning btn-sm" onclick="abrirModalAtualizar(${organizador.id})">Atualizar</button>
                <button class="btn btn-danger btn-sm" onclick="deletarOrganizador(${organizador.id})">Deletar</button>
            </td>
        `;
        tbody.appendChild(tr);
    });
}

async function criarOrganizador() {
    const nome = document.getElementById('nome').value;
    const contato = document.getElementById('contato').value;

    const novoOrganizador = {
        nome: nome,
        contato: contato
    };

    const response = await fetch(baseUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(novoOrganizador)
    });

    if (response.ok) {
        alert('Organizador criado com sucesso!');
        listarOrganizadores();
        $('#organizadorModal').modal('hide');
    } else {
        alert('Erro ao criar organizador.');
    }
}

async function abrirModalAtualizar(id) {
    const response = await fetch(`${baseUrl}/${id}`);
    const organizador = await response.json();

    document.getElementById('atualizar-id').value = organizador.id;
    document.getElementById('atualizar-nome').value = organizador.nome;
    document.getElementById('atualizar-contato').value = organizador.contato;

    $('#modalAtualizar').modal('show');
}

async function atualizarOrganizador() {
    const id = document.getElementById('atualizar-id').value;
    const nome = document.getElementById('atualizar-nome').value;
    const contato = document.getElementById('atualizar-contato').value;

    const organizadorAtualizado = {
        id: id,
        nome: nome,
        contato: contato
    };

    const response = await fetch(`${baseUrl}/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(organizadorAtualizado)
    });

    if (response.ok) {
        alert('Organizador atualizado com sucesso!');
        listarOrganizadores();
        $('#modalAtualizar').modal('hide');
    } else {
        alert('Erro ao atualizar organizador.');
    }
}

async function deletarOrganizador(id) {
    if (confirm('Tem certeza que deseja deletar este organizador?')) {
        const response = await fetch(`${baseUrl}/${id}`, {
            method: 'DELETE'
        });

        if (response.ok) {
            alert('Organizador deletado com sucesso!');
            listarOrganizadores();
        } else {
            alert('Erro ao deletar organizador.');
        }
    }
}

$(document).ready(function () {
    listarOrganizadores();
});