Projeto InfNet - Arquitetura Java [24E4_2]

# Sistema de Gerenciamento de Eventos

Este projeto é um Sistema de Gerenciamento de Eventos desenvolvido em Java utilizando Spring Boot e Lombok para simplificar 
a criação de classes e manipulação de dados. O sistema permite a criação e gerenciamento de eventos, organizadores, participantes, 
ingressos e localizações.

## Executar
- ** Temos 2 profiles, quando usamos dev a classe dataLoader carrega de um arquivo texto os dados e exibe no console os objetos, o banco em dev será o H2.
- ** Quando utilizamos o profile prod a classe dataLoader não é executada e o banco é alterado de H2 para MySQL.


## Funcionalidades

- **Cadastro de Eventos**: Gerencia eventos com informações como nome, descrição, local, data, assentos disponíveis, organizador e preço.
- **Cadastro de Organizadores**: Criação e gerenciamento de organizadores com seus respectivos detalhes de contato.
- **Cadastro de Participantes**: Registro de participantes com nome, e-mail e telefone.
- **Emissão de Ingressos**: Facilita a emissão e controle de ingressos vinculados a participantes em eventos.
- **Gestão de Localizações**: Armazena e gerencia informações sobre os locais onde os eventos ocorrem.

## Estrutura do Projeto

### Classes

- **Evento**: Representa um evento com atributos como nome, descrição, localização, data, organizador, assentos disponíveis e preço.
- **Organizador**: Representa o organizador responsável pelo evento, com detalhes como nome e e-mail.
- **Participante**: Representa um participante de um evento, armazenando informações de contato.
- **Ingresso**: Representa um ingresso emitido para um participante, indicando o evento e se o pagamento foi realizado.
- **Localizacao**: Armazena os detalhes do local onde o evento será realizado, como endereço, cidade e estado.
- **DataLoader**: Classe responsável por popular o sistema com eventos, participantes, ingressos e outras informações iniciais.

### Interface

- **EventoManager**: Define os métodos para gerenciar eventos.
    - `criarEvento(Evento evento)`: Cria um novo evento.
    - `atualizarEvento(Evento evento)`: Atualiza os dados de um evento existente.
    - `deletarEvento(Long id)`: Remove um evento pelo ID.
    - `buscarEventoPorId(Long id)`: Busca um evento pelo ID.
    - `listarEventos()`: Lista todos os eventos.
    - `venderIngresso(Long eventoId)`: Realiza a venda de ingressos para um evento.

- **IngressoManager**: Define os métodos para gerenciar ingressos.
    - `emitirIngresso(Ingresso ingresso)`: Emite um ingresso para um evento.
    - `atualizarIngresso(Ingresso ingresso)`: Atualiza os detalhes de um ingresso.
    - `deletarIngresso(Long id)`: Remove um ingresso pelo ID.
    - `buscarIngressoPorId(Long id)`: Busca um ingresso pelo ID.
    - `listarIngressos()`: Lista todos os ingressos emitidos.

### Repositórios

As classes de repositório utilizam a extensão de `JpaRepository` para realizar as operações de CRUD nas entidades do sistema.

- **EventoRepository**: Interface para operações CRUD na entidade `Evento`.
- **OrganizadorRepository**: Interface para operações CRUD na entidade `Organizador`.
- **ParticipanteRepository**: Interface para operações CRUD na entidade `Participante`.
- **IngressoRepository**: Interface para operações CRUD na entidade `Ingresso`.
- **LocalizacaoRepository**: Interface para operações CRUD na entidade `Localizacao`.

## Controladores REST

### **EventoController**

Define as operações relacionadas à criação, listagem, atualização e exclusão de eventos, além da venda de ingressos.

- **Criar Evento** (`POST /eventos`): Cria um novo evento.
- **Listar Eventos** (`GET /eventos`): Lista todos os eventos.
- **Buscar Evento por ID** (`GET /eventos/{id}`): Busca um evento pelo seu ID.
- **Atualizar Evento** (`PUT /eventos/{id}`): Atualiza os detalhes de um evento existente.
- **Deletar Evento** (`DELETE /eventos/{id}`): Remove um evento pelo ID.
- **Vender Ingresso** (`POST /eventos/{id}/vender-ingresso`): Vende um ingresso para o evento especificado.

### **IngressoController**

Define as operações relacionadas à emissão, listagem, atualização e exclusão de ingressos.

- **Emitir Ingresso** (`POST /ingressos`): Emite um ingresso para um participante em um evento.
- **Listar Ingressos** (`GET /ingressos`): Lista todos os ingressos emitidos.
- **Buscar Ingresso por ID** (`GET /ingressos/{id}`): Busca um ingresso pelo seu ID.
- **Atualizar Ingresso** (`PUT /ingressos/{id}`): Atualiza os detalhes de um ingresso.
- **Deletar Ingresso** (`DELETE /ingressos/{id}`): Remove um ingresso pelo ID.

### **LocalizacaoController**

Define as operações relacionadas à criação, atualização, listagem e exclusão de localizações.

- **Criar Localização** (`POST /localizacoes`): Cria uma nova localização.
- **Listar Localizações** (`GET /localizacoes`): Lista todas as localizações.
- **Buscar Localização por ID** (`GET /localizacoes/{id}`): Busca uma localização pelo seu ID.
- **Atualizar Localização** (`PUT /localizacoes/{id}`): Atualiza uma localização existente.
- **Deletar Localização** (`DELETE /localizacoes/{id}`): Remove uma localização pelo ID.

### **OrganizadorController**

Define as operações relacionadas à criação, listagem, atualização e exclusão de organizadores.

- **Criar Organizador** (`POST /organizadores`): Cria um novo organizador.
- **Listar Organizadores** (`GET /organizadores`): Lista todos os organizadores.
- **Buscar Organizador por ID** (`GET /organizadores/{id}`): Busca um organizador pelo seu ID.
- **Atualizar Organizador** (`PUT /organizadores/{id}`): Atualiza um organizador existente.
- **Deletar Organizador** (`DELETE /organizadores/{id}`): Remove um organizador pelo ID.

### **ParticipanteController**

Define as operações relacionadas ao registro, listagem, atualização e exclusão de participantes.

- **Registrar Participante** (`POST /participantes`): Registra um novo participante.
- **Listar Participantes** (`GET /participantes`): Lista todos os participantes.
- **Buscar Participante por ID** (`GET /participantes/{id}`): Busca um participante pelo seu ID.
- **Atualizar Participante** (`PUT /participantes/{id}`): Atualiza os dados de um participante.
- **Deletar Participante** (`DELETE /participantes/{id}`): Remove um participante pelo ID.
