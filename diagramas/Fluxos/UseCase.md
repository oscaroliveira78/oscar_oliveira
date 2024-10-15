# Casos de Uso do Sistema de Gerenciamento de Eventos

## Caso de Uso: Cadastrar Evento

- **ID**: UC-01
- **Ator**: Organizador
- **Descrição**: O organizador cadastra um novo evento no sistema.

### Pré-requisitos:
- O organizador deve estar autenticado no sistema.

### Fluxo Principal:
1. O organizador acessa a opção de cadastrar evento.
2. O sistema exibe o formulário de cadastro de evento.
3. O organizador preenche os detalhes do evento (nome, descrição, data/hora, local, quantidade de assentos, preço).
4. O organizador submete o formulário.
5. O sistema valida os dados.
6. O sistema armazena o evento na base de dados.
7. O sistema confirma o cadastro do evento.

### Fluxo Alternativo:
- **A1**: Se os dados não forem válidos:
  - O sistema exibe uma mensagem de erro e solicita correções.

### Pós-condição:
- O evento está cadastrado e disponível para visualização e compra.

---

## Caso de Uso: Cadastrar Participante

- **ID**: UC-02
- **Ator**: Participante
- **Descrição**: Um participante se cadastra no sistema para participar dos eventos.

### Pré-requisitos:
- O participante deve acessar a página de cadastro.

### Fluxo Principal:
1. O participante acessa a opção de cadastrar-se.
2. O sistema exibe o formulário de cadastro.
3. O participante preenche os dados (nome, e-mail, telefone).
4. O participante submete o formulário.
5. O sistema valida os dados.
6. O sistema armazena o participante na base de dados.
7. O sistema confirma o cadastro.

### Fluxo Alternativo:
- **A1**: Se os dados não forem válidos:
  - O sistema exibe uma mensagem de erro e solicita correções.

### Pós-condição:
- O participante está cadastrado e pode visualizar e comprar ingressos para eventos.

---

## Caso de Uso: Comprar Ingresso

- **ID**: UC-03
- **Ator**: Participante
- **Descrição**: O participante compra um ingresso para um evento.

### Pré-requisitos:
- O participante deve estar autenticado e ter um cadastro ativo.
- O evento deve estar disponível para compra.

### Fluxo Principal:
1. O participante acessa a lista de eventos disponíveis.
2. O sistema exibe a lista de eventos.
3. O participante seleciona um evento.
4. O sistema exibe os detalhes do evento, incluindo a opção de compra de ingresso.
5. O participante solicita a compra de ingresso.
6. O sistema verifica se há assentos disponíveis, considerando a quantidade de ingressos vendidos.
7. O sistema cria um ingresso associado ao participante e ao evento.
8. O sistema confirma a compra e envia os detalhes do ingresso.

### Fluxo Alternativo:
- **A1**: Se não houver ingressos disponíveis:
  - O sistema informa ao participante sobre a falta de ingressos e bloqueia a compra.

### Pós-condição:
- O ingresso é reservado para o participante e registrado no sistema.

---

## Caso de Uso: Visualizar Eventos

- **ID**: UC-04
- **Ator**: Participante e Organizador
- **Descrição**: Os usuários podem visualizar a lista de eventos disponíveis.

### Pré-requisitos:
- Não há pré-requisitos.

### Fluxo Principal:
1. O usuário acessa a opção de visualizar eventos.
2. O sistema exibe a lista de eventos disponíveis.
3. O usuário pode selecionar um evento para ver mais detalhes.

### Fluxo Alternativo:
- Não aplicável.

### Pós-condição:
- O usuário visualiza a lista de eventos e detalhes conforme necessário.

---

## Caso de Uso: Gerenciar Ingressos

- **ID**: UC-05
- **Ator**: Organizador
- **Descrição**: O organizador pode gerenciar os ingressos de seus eventos.

### Pré-requisitos:
- O organizador deve estar autenticado no sistema.

### Fluxo Principal:
1. O organizador acessa a opção de gerenciar ingressos.
2. O sistema exibe a lista de eventos do organizador.
3. O organizador seleciona um evento.
4. O sistema exibe os detalhes dos ingressos para o evento.
5. O organizador pode visualizar, editar ou excluir ingressos.

### Fluxo Alternativo:
- Não aplicável.

### Pós-condição:
- As informações dos ingressos são atualizadas conforme as ações do organizador.

---

## Caso de Uso: Visualizar Participantes

- **ID**: UC-06
- **Ator**: Organizador
- **Descrição**: O organizador pode visualizar a lista de participantes inscritos em seu evento.

### Pré-requisitos:
- O organizador deve estar autenticado no sistema.

### Fluxo Principal:
1. O organizador acessa a opção de visualizar participantes.
2. O sistema exibe a lista de eventos do organizador.
3. O organizador seleciona um evento.
4. O sistema exibe a lista de participantes inscritos.

### Fluxo Alternativo:
- Não aplicável.

### Pós-condição:
- O organizador visualiza a lista de participantes inscritos no evento.

---

## Caso de Uso: Gerenciar Disponibilidade de Ingressos

- **ID**: UC-07
- **Ator**: Organizador
- **Descrição**: O organizador pode gerenciar a disponibilidade dos ingressos de seus eventos, visualizando a quantidade de ingressos vendidos e disponíveis.

### Pré-requisitos:
- O organizador deve estar autenticado no sistema.

### Fluxo Principal:
1. O organizador acessa a opção de gerenciar disponibilidade de ingressos.
2. O sistema exibe a lista de eventos do organizador.
3. O organizador seleciona um evento.
4. O sistema exibe a quantidade total de assentos e ingressos vendidos.
5. O organizador pode alterar a quantidade total de assentos ou ajustar os ingressos disponíveis.

### Fluxo Alternativo:
- Não aplicável.

### Pós-condição:
- As informações de disponibilidade dos ingressos são atualizadas.
