# Projeto de Consulta de Pedidos

Este projeto é responsável pela consulta e manipulação de pedidos utilizando Spring Boot, Redis para verificar duplicidade de pedidos e integração com RabbitMQ para comunicação assíncrona.

## Tecnologias

- Java 21+
- Spring Boot 3.4.1
- Spring Data JPA
- Redis
- RabbitMQ
- Docker
- Docker Compose

## Estrutura do Projeto

O projeto é composto pelos seguintes serviços:

### 1. **PedidoConsultation (API de Consulta de Pedidos)**
   - Expõe os endpoints para consulta de pedidos.
   
### 2. **PedidoListener (Listener de Mensagens RabbitMQ)**
   - Escuta mensagens de fila para processar pedidos.
   - Manipula o recebimento de pedidos e comunicação com RabbitMQ.
   
### 3. **PedidoProducer (Producer de Mensagens RabbitMQ)**
   - Envia mensagens de pedidos para a fila RabbitMQ.

### 4. **Pedido (Modelo de Dados)**
   - Define a estrutura de dados do pedido, incluindo número de pedido e lista de produtos.

## Como Levantar o Docker Compose

O projeto utiliza Docker Compose para orquestrar os containers necessários, incluindo Postgres, Redis e RabbitMQ.

### Passo a Passo para Subir o Ambiente

1. **Certifique-se de ter o Docker e o Docker Compose instalados.**

2. **Baixe o repositório e acesse o diretório do projeto:**

3. **Suba os containers com Docker Compose:**
    ```bash
    docker-compose up --build
    ```

    Isso irá levantar os containers para Postgres, Redis e RabbitMQ.

4. **Para parar os containers:**
    ```bash
    docker-compose down
    ```

## Rotas Disponíveis

### 1. **Consulta de Todos os Pedidos**
   - **URL:** `http://localhost:8083/pedidos?page=0&size=10`
   - **Método:** `GET`
   - **Descrição:** Retorna todos os pedidos com paginação.
   - **Exemplo de Resposta:**
     ```json
     [
       {
         "numeroPedido": "99",
         "produtos": [
           {"nome": "Produto 1", "preco": 100.00},
           {"nome": "Produto 2", "preco": 200.00}
         ]
       },
       ...
     ]
     ```

### 2. **Consulta de Pedidos por Status**
   - **URL:** `http://localhost:8083/pedidos?status=PROCESSADO&page=0&size=5`
   - **Método:** `GET`
   - **Descrição:** Retorna os pedidos com o status especificado.
   - **Exemplo de Resposta:**
     ```json
     [
       {
         "numeroPedido": "99",
         "produtos": [
           {"nome": "Produto 1", "preco": 100.00},
           {"nome": "Produto 2", "preco": 200.00}
         ]
       },
       ...
     ]
     ```

### 3. **Criação de Novo Pedido**
   - **URL:** `http://localhost:8082/pedidos`
   - **Método:** `POST`
   - **Corpo da Requisição (JSON):**
     ```json
     {
       "numeroPedido": "99",
       "produtos": [
         { "nome": "Produto 1", "preco": 100.00 },
         { "nome": "Produto 2", "preco": 200.00 }
       ]
     }
     ```
   - **Descrição:** Cria um novo pedido com uma lista de produtos.
   - **Exemplo de Resposta:**
     ```json
     {
       "status": "Pedido Criado com Sucesso",
       "numeroPedido": "99"
     }
     ```

## Estrutura dos Serviços

### 1. **PedidoConsultation**
   - Este serviço é responsável por expor os endpoints REST para consulta dos pedidos, como mostrado acima.
   - Ele utiliza o Redis para cache das consultas, o que melhora o desempenho em leituras subsequentes.

### 2. **PedidoListener**
   - Este serviço escuta a fila RabbitMQ em busca de novas mensagens de pedidos.
   - Quando uma nova mensagem é recebida, o listener processa a criação de pedidos ou atualizações no sistema.

### 3. **PedidoProducer**
   - Este serviço é responsável por enviar mensagens para a fila RabbitMQ, notificando o `PedidoListener` sobre a criação de novos pedidos ou atualizações.

## Licença

Este projeto é licenciado sob a [MIT License](LICENSE).
