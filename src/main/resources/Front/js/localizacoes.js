const baseUrl = 'http://localhost:8182/localizacoes'; // Substitua pela URL correta da sua API

async function listarLocalizacoes() {
    try {
        const response = await fetch(baseUrl);

        // Verifique se a resposta foi bem-sucedida
        if (!response.ok) {
            throw new Error(`Erro ao buscar localizações: ${response.status} ${response.statusText}`);
        }

        // Aguarde a resolução da Promise
        const localizacoes = await response.json();
        console.log(localizacoes);
        const tbody = document.getElementById('localizacoes-tbody');
        tbody.innerHTML = '';

        // Verifique se a resposta contém localizações
        if (localizacoes.length === 0) {
            const tr = document.createElement('tr');
            tr.innerHTML = `<td colspan="5">Nenhuma localização encontrada.</td>`;
            tbody.appendChild(tr);
            return; // Saia da função se não houver localizações
        }

        localizacoes.forEach(localizacao => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
        <td>${localizacao.id}</td>
        <td>${localizacao.endereco}</td>
        <td>${localizacao.cidade}</td>
        <td>${localizacao.estado}</td>
        <td>
            <button class="btn btn-warning btn-sm" onclick="abrirModalAtualizar(${localizacao.id})">Atualizar</button>
            <button class="btn btn-danger btn-sm" onclick="deletarLocalizacao(${localizacao.id})">Deletar</button>
        </td>
    `;
            tbody.appendChild(tr);
        });
    } catch (error) {
        console.error('Erro ao listar localizações:', error);
        alert('Erro ao listar localizações. Verifique o console para mais detalhes.'); // Exibe uma mensagem ao usuário
    }
}

async function criarLocalizacao() {
    const endereco = document.getElementById('endereco').value;
    const cidade = document.getElementById('cidade').value;
    const estado = document.getElementById('estado').value;

    const novaLocalizacao = {
        endereco: endereco,
        cidade: cidade,
        estado: estado
    };

    const response = await fetch(baseUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(novaLocalizacao)
    });

    if (response.ok) {
        alert('Localização criada com sucesso!');
        listarLocalizacoes();
        $('#localizacaoModal').modal('hide');
    } else {
        alert('Erro ao criar localização.');
    }
}

async function abrirModalAtualizar(id) {
    const response = await fetch(`${baseUrl}/${id}`);
    const localizacao = await response.json();

    document.getElementById('atualizar-id').value = localizacao.id;
    document.getElementById('atualizar-endereco').value = localizacao.endereco;
    document.getElementById('atualizar-cidade').value = localizacao.cidade;
    document.getElementById('atualizar-estado').value = localizacao.estado;

    $('#modalAtualizar').modal('show');
}

async function atualizarLocalizacao() {
    const id = document.getElementById('atualizar-id').value;
    const endereco = document.getElementById('atualizar-endereco').value;
    const cidade = document.getElementById('atualizar-cidade').value;
    const estado = document.getElementById('atualizar-estado').value;

    const localizacaoAtualizada = {
        id: id,
        endereco: endereco,
        cidade: cidade,
        estado: estado
    };

    const response = await fetch(`${baseUrl}/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(localizacaoAtualizada)
    });

    if (response.ok) {
        alert('Localização atualizada com sucesso!');
        listarLocalizacoes();
        $('#modalAtualizar').modal('hide');
    } else {
        alert('Erro ao atualizar localização.');
    }
}

async function deletarLocalizacao(id) {
    if (confirm('Tem certeza que deseja deletar esta localização?')) {
        const response = await fetch(`${baseUrl}/${id}`, {
            method: 'DELETE'
        });

        if (response.ok) {
            alert('Localização deletada com sucesso!');
            listarLocalizacoes();
        } else {
            alert('Erro ao deletar localização.');
        }
    }
}

$(document).ready(function () {
    listarLocalizacoes();
});