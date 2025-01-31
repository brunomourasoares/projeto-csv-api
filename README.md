# API CSV

## Descrição
A aplicação **API CSV** é um serviço RESTful desenvolvido em Java que permite a importação de dados de arquivos CSV para um banco de dados PostgreSQL. Utiliza o framework Jersey para a criação dos endpoints REST e o Yasson para a serialização e desserialização de objetos JSON.

## Funcionalidades
- **Importação de CSV**: Permite a importação de dados de arquivos CSV para o banco de dados.
- **Consulta de Dados**: Oferece endpoints para consulta dos dados importados.
- **Gerenciamento de Transações**: Utiliza a API de transações Java (JTA) para garantir a integridade dos dados.

## Tecnologias Utilizadas
- **Java 1.8**
- **Maven**
- **Jersey (JAX-RS)**
- **PostgreSQL**
- **Yasson (JSON-B)**
- **Tomcat Servlet API**
- **JTA (Java Transaction API)**

## Estrutura do Projeto
- **`pom.xml`**: Configurações do Maven e dependências do projeto.
- **`src/main/webapp/WEB-INF/beans.xml`**: Configurações de beans para o CDI.
- **`src/main/webapp/WEB-INF/web.xml`**: Configurações do servlet e mapeamento de URLs.

## Como Executar
1. **Clone o repositório**:
    ```sh
    git clone https://github.com/seu-usuario/api-csv.git
    cd api-csv
    ```

2. **Compile o projeto**:
    ```sh
    mvn clean install
    ```

3. **Implante o arquivo WAR** no seu servidor de aplicação (Tomcat, por exemplo).

4. **Acesse a aplicação**:
    ```
    http://localhost:8080/api-csv/api/
    ```

## Endpoints Principais
- **Importar CSV**: `POST /api/import`
- **Consultar Dados**: `GET /api/data`

## Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests.