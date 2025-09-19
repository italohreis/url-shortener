# URL Shortener API 🔗

Uma API para encurtamento de URLs, construída com Java, Spring Boot e Docker. O projeto transforma URLs longas em links curtos e gerenciáveis, além de fornecer um sistema de analytics para rastreamento de cliques.

---

## 🚀 Features

* **Encurtamento de URL:** Converte URLs longas em códigos curtos e únicos usando codificação Base62.
* **Serviço de Redirecionamento:** Redireciona usuários do link curto para a URL original.
* **Analytics de Cliques:** Rastreia cada clique, coletando dados como timestamp, User-Agent e Referrer.
* **Endpoint de Analytics:** Expõe os dados de cliques coletados através de uma API REST.
* **Cache:** Utiliza caching (`@Cacheable`) para otimizar a performance dos redirecionamentos.
* **Documentação da API:** A API é autodocumentada usando Springdoc OpenAPI (Swagger).
* **Containerização:** Ambiente de desenvolvimento e produção totalmente containerizado com Docker e Docker Compose.
* **Migrations de Banco de Dados:** Gerenciamento de schema do banco de dados de forma versionada com Flyway.

---

## 🛠️ Tecnologias Utilizadas

* **Backend:**
    * Java 21
    * Spring Boot 3.x
    * Spring Data JPA / Hibernate
    * Spring Cache
* **Banco de Dados:**
    * PostgreSQL
    * Flyway (para migrations)
* **Ferramentas de Build e Dependências:**
    * Maven
    * Lombok
* **Documentação:**
    * Springdoc OpenAPI (Swagger UI)
* **Ambiente:**
    * Docker & Docker Compose

---

## 🏁 Como Começar

Siga os passos abaixo para configurar e executar o projeto localmente.

### Pré-requisitos

* [JDK 21](https://www.oracle.com/java/technologies/downloads/#java21)
* [Docker](https://www.docker.com/products/docker-desktop/)
* [Docker Compose](https://docs.docker.com/compose/install/)

### Instalação e Execução

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/italohreis/url-shortener.git
    cd url-shortener
    ```

2.  **Configure as Variáveis de Ambiente:**
    Crie um arquivo chamado `.env` na raiz do projeto, copiando o conteúdo do arquivo `.env.example`. Preencha as variáveis com suas credenciais.

3.  **Suba os Containers:**
    Com o Docker Desktop em execução, execute o seguinte comando na raiz do projeto:
    ```bash
    docker-compose up --build -d
    ```
    * O comando irá construir a imagem da aplicação a partir do `Dockerfile` e iniciar os containers da API e do banco de dados em segundo plano (`-d`).

4.  **Verifique se está tudo rodando:**
    ```bash
    docker ps
    ```
    Você deve ver os containers `url-shortener` e `url-shortener-db` com o status "Up".

A aplicação estará acessível em `http://localhost:8080`.

---

## 📖 Documentação da API

A API está documentada com Swagger UI. Após iniciar a aplicação, acesse o seguinte endereço no seu navegador para ver a documentação interativa e testar os endpoints:

**[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)**

### Principais Endpoints

* `POST /api/url/shorten`: Cria uma nova URL encurtada.
* `GET /{shortUrl}`: Redireciona para a URL original.
* `GET /api/url/analytics/{shortUrl}`: Retorna as estatísticas de um link encurtado.

---
