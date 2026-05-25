# 🛒 Online Shop Microservices

Um ecossistema completo de microsserviços para e-commerce desenvolvido com **Spring Boot 4 / Java 21**, utilizando arquitetura distribuída, gerenciamento centralizado de configurações, descoberta de serviços automática e bancos de dados NoSQL integrados via **Docker Compose**.

---

## 🏗️ Arquitetura do Sistema

O projeto é dividido em 5 componentes principais que se comunicam de forma transparente através da rede interna do Docker:

1. **`Config Server` (Porta 8888):** Servidor centralizado de configurações configurado em modo `native`. Ele gerencia os arquivos `.yml` de propriedades de todos os outros serviços.
2. **`Service Discovery` (Porta 8761):** Servidor Eureka que atua como catálogo de serviços, permitindo o balanceamento de carga dinâmico via Spring Cloud LoadBalancer.
3. **`Client Service` (Porta 8081 / Interna 8080):** Microsserviço responsável pelo gerenciamento de clientes e persistência no MongoDB (`client_db`).
4. **`Product Service` (Porta 8082 / Interna 8080):** Microsserviço que gerencia o catálogo de produtos e persistência no MongoDB (`product_db`).
5. **`Sale Service` (Porta 8083 / Interna 8080):** Microsserviço de vendas que consome dados de clientes e produtos via **Feign Client** para processar e registrar pedidos no MongoDB (`sale_db`).

---

## 🛠️ Tecnologias Utilizadas

* **Java 21** & **Spring Boot 4**
* **Spring Cloud Config** & **Spring Cloud Netflix Eureka**
* **Spring Cloud OpenFeign** & **Spring Cloud LoadBalancer**
* **Spring Data MongoDB**
* **Springdoc OpenAPI (Swagger UI)** para documentação das APIs
* **Docker** & **Docker Compose**

---

## ⚙️ Pré-requisitos

Antes de iniciar, certifique-se de ter instalado em sua máquina:

* JDK 21
* Maven 3.x ou o Maven Wrapper (`./mvnw`) incluído nos projetos
* Docker Desktop

---

## 🚀 Como Executar o Projeto

### 1. Clonar o repositório e acessar a raiz

```bash
git clone https://github.com/jawc-05/online-shop-microservices.git
cd online-shop-microservices
```

### 2. Compilar todos os microsserviços

Execute o comando de empacotamento em cada um dos diretórios para gerar os arquivos `.jar` necessários para as imagens Docker:

```bash
cd ConfigServer && ./mvnw clean package -DskipTests && cd ..
cd ServiceDiscovery && ./mvnw clean package -DskipTests && cd ..
cd ClientService && ./mvnw clean package -DskipTests && cd ..
cd ProductService && ./mvnw clean package -DskipTests && cd ..
cd SaleService && ./mvnw clean package -DskipTests && cd ..
```

### 3. Subir o ecossistema com Docker Compose

Na raiz do projeto (onde está o arquivo `docker-compose.yml`), execute:

```bash
docker compose up --build
```

---

## 📌 Links Úteis e Portas Locais

Após o boot bem-sucedido de toda a infraestrutura, você pode acessar os seguintes serviços no seu navegador:

| Serviço | URL Local | Descrição |
|---------|-----------|-----------|
| Eureka Server | http://localhost:8761 | Painel de monitoramento das instâncias ativas |
| Config Server | http://localhost:8888/client-service/default | Endpoint para validar as propriedades servidas |
| Client Swagger | http://localhost:8081/swagger-ui.html | Documentação e testes das rotas de Clientes |
| Product Swagger | http://localhost:8082/swagger-ui.html | Documentação e testes das rotas de Produtos |
| Sale Swagger | http://localhost:8083/swagger-ui.html | Documentação e testes das rotas de Vendas |

---

## 🧪 Fluxo Básico de Teste (Mesa Limpa)

Como o banco de dados MongoDB inicializa vazio, siga esta ordem para testar o fluxo de uma venda completa:

### 1. Cadastrar um Cliente

Faça um POST para `http://localhost:8081/client` ou use o Swagger de Clientes. Copie o ID gerado na resposta.

### 2. Cadastrar um Produto

Faça um POST para `http://localhost:8082/product` criando um item válido.

### 3. Realizar a Venda

Envie a requisição de venda para o sale-service (`http://localhost:8083/sale`), colando o ID real do cliente gerado no passo 1:

```json
{
  "code": "01",
  "clientId": "COLE_O_ID_AQUI",
  "saleDate": "2026-05-25"
}
```

O serviço validará a existência do cliente via comunicação síncrona interna e retornará status `201 Created`.

---

## 📝 Licença

Este projeto está disponível para uso e contribuições. Para mais informações, abra uma issue no repositório.
