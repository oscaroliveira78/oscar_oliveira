const apiBaseUrl = 'http://localhost:8182/eventos'; // altere para a url da sua api
const apiOrganizadoresUrl = 'http://localhost:8182/organizadores'; // altere para a url da sua api de organizadores
const apiLocalizacoesUrl = 'http://localhost:8182/localizacoes'; // altere para a url da sua api de localizações

// função para carregar eventos na tabela
function carregarEventos() {
    fetch(apiBaseUrl)
        .then(response => response.json())
        .then(eventos => {
            const tabelaEventos = document.querySelector('#tabelaEventos tbody');
            tabelaEventos.innerHTML = ''; // limpa a tabela antes de adicionar novos eventos

            eventos.forEach(evento => {
                const row = document.createElement('tr');

                row.innerHTML = `
                    <td>${evento.id}</td>
                    <td>${evento.nome}</td>
                    <td>${new Date(evento.dataHora).toLocaleString()}</td>
                    <td>${evento.qtdeVendida} / ${evento.qtdeAssentos}</td>
                    <td>R$ ${evento.preco.toFixed(2)}</td>
                    <td>
                        <button class="btn btn-warning btn-sm" onclick="abrirModalEditar(${evento.id})">Editar</button>
                        <button class="btn btn-danger btn-sm" onclick="deletarEvento(${evento.id})">Excluir</button>
                    </td>
                `;

                tabelaEventos.appendChild(row);
            });
        });
}

// função para carregar organizadores no select
function carregarOrganizadores() {
    fetch(apiOrganizadoresUrl)
        .then(response => response.json())
        .then(organizadores => {
            const selectOrganizador = document.getElementById('organizador');
            const editarSelectOrganizador = document.getElementById('editarOrganizador');

            // Limpa as opções existentes
            selectOrganizador.innerHTML = '';
            editarSelectOrganizador.innerHTML = '';

            // Adiciona a opção padrão
            const optionDefault = document.createElement('option');
            optionDefault.value = '';
            optionDefault.textContent = 'Selecione um organizador';
            selectOrganizador.appendChild(optionDefault);
            editarSelectOrganizador.appendChild(optionDefault.cloneNode(true));

            organizadores.forEach(org => {
                const option = document.createElement('option');
                option.value = org.id; // Assumindo que o objeto organizador tem um atributo id
                option.textContent = org.nome; // Assumindo que o objeto organizador tem um atributo nome
                selectOrganizador.appendChild(option);

                // Preenchendo o select do modal de edição
                const editarOption = option.cloneNode(true);
                editarSelectOrganizador.appendChild(editarOption);
            });
        })
        .catch(error => console.error('Erro ao carregar organizadores:', error));
}

// função para carregar localizações no select
function carregarLocalizacoes() {
    fetch(apiLocalizacoesUrl)
        .then(response => response.json())
        .then(localizacoes => {
            console.log(localizacoes); // Para depuração
            const selectLocalizacao = document.getElementById('localizacao');
            const editarSelectLocalizacao = document.getElementById('editarLocalizacao');

            // Limpa as opções existentes
            selectLocalizacao.innerHTML = '';
            editarSelectLocalizacao.innerHTML = '';

            // Adiciona a opção padrão
            const optionDefault = document.createElement('option');
            optionDefault.value = '';
            optionDefault.textContent = 'Selecione uma localização';
            selectLocalizacao.appendChild(optionDefault);
            editarSelectLocalizacao.appendChild(optionDefault.cloneNode(true));

            localizacoes.forEach(loc => {
                const option = document.createElement('option');
                option.value = loc.id; // Assumindo que o objeto localização tem um atributo id
                option.textContent = loc.endereco; // Assumindo que o objeto localização tem um atributo nome
                selectLocalizacao.appendChild(option);

                // Preenchendo o select do modal de edição
                const editarOption = option.cloneNode(true);
                editarSelectLocalizacao.appendChild(editarOption);
            });
        })
        .catch(error => console.error('Erro ao carregar localizações:', error));
}

// função para criar evento
document.getElementById('formEvento').addEventListener('submit', function (event) {
    event.preventDefault();

    const evento = {
        nome: document.getElementById('nome').value,
        descricao: document.getElementById('descricao').value,
        dataHora: new Date(document.getElementById('dataHora').value).toISOString(),
        qtdeAssentos: document.getElementById('qtdeAssentos').value,
        preco: parseFloat(document.getElementById('preco').value),
        organizador: { id: document.getElementById('organizador').value },
        localizacao: { id: document.getElementById('localizacao').value }
    };

    fetch(apiBaseUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(evento)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao criar evento');
            }
            return response.json();
        })
        .then(() => {
            carregarEventos(); // recarrega os eventos
            document.getElementById('formEvento').reset(); // limpa o formulário
        })
        .catch(error => console.error('Erro:', error));
});

// função para abrir o modal de edição
function abrirModalEditar(id) {
    fetch(`${apiBaseUrl}/${id}`)
        .then(response => response.json())
        .then(evento => {
            document.getElementById('editarId').value = evento.id;
            document.getElementById('editarNome').value = evento.nome;
            document.getElementById('editarDescricao').value = evento.descricao;
            document.getElementById('editarDataHora').value = new Date(evento.dataHora).toISOString().slice(0, 16);
            document.getElementById('editarQtdeAssentos').value = evento.qtdeAssentos;
            document.getElementById('editarPreco').value = evento.preco.toFixed(2);
            document.getElementById('editarOrganizador').value = evento.organizador.id; // Preenche o organizador
            document.getElementById('editarLocalizacao').value = evento.localizacao.id; // Preenche a localização
            $('#modalEditarEvento').modal('show'); // Mostra o modal
        })
        .catch(error => console.error('Erro ao abrir modal de edição:', error));
}

// função para salvar alterações do evento
document.getElementById('formEditarEvento').addEventListener('submit', function (event) {
    event.preventDefault();

    const id = document.getElementById('editarId').value;
    const evento = {
        nome: document.getElementById('editarNome').value,
        descricao: document.getElementById('editarDescricao').value,
        dataHora: new Date(document.getElementById('editarDataHora').value).toISOString(),
        qtdeAssentos: document.getElementById('editarQtdeAssentos').value,
        preco: parseFloat(document.getElementById('editarPreco').value),
        organizador: { id: document.getElementById('editarOrganizador').value },
        localizacao: { id: document.getElementById('editarLocalizacao').value }
    };

    fetch(`${apiBaseUrl}/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(evento)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao editar evento');
            }
            return response.json();
        })
        .then(() => {
            carregarEventos(); // recarrega os eventos
            $('#modalEditarEvento').modal('hide'); // fecha o modal
        })
        .catch(error => console.error('Erro:', error));
});

// função para deletar evento
function deletarEvento(id) {
    if (confirm('Tem certeza que deseja excluir este evento?')) {
        fetch(`${apiBaseUrl}/${id}`, {
            method: 'DELETE'
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro ao excluir evento');
                }
                carregarEventos(); // recarrega os eventos
            })
            .catch(error => console.error('Erro:', error));
    }
}

// inicializa a página
window.onload = function () {
    carregarEventos();
    carregarOrganizadores();
    carregarLocalizacoes();
};