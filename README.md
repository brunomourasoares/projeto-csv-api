# API CSV

## Descrição
A aplicação **API CSV** é um serviço RESTful desenvolvido em Java que permite a importação de dados de arquivos CSV para um banco de dados PostgreSQL. Utiliza o framework Jersey para a criação dos endpoints REST.

## Funcionalidades
1. **Importação de arquivos CSV**:

Permite a importação de dados de arquivos CSV para o banco de dados.

2. **Exportar os dados do banco de dados em arquivo de texto**:

Oferece endpoints para consulta dos dados importados.

## Tecnologias Utilizadas
- **Java 1.8**
- **Maven**
- **Jersey (JAX-RS)**
- **PostgreSQL**
- **JBoss**

## Estrutura do Projeto
```
|-- src
|   |-- main
|      |-- java
|         |-- br
|            |-- com
|               |-- apicsv
|                  |-- controllers
|                     |-- UserController.java
|                  |-- models
|                     |-- User.java
|                  |-- repositories
|                     |-- UserRepository.java
|                  |-- Services
|                     |-- UserService.java
|                  |-- App.java
|      |-- resources
|         |-- META-INF
|            |-- beans.xml
|-- data
|   |-- lista.csv
|-- .gitignore
|-- pom.xml
|-- README.md
```

## Como Executar
1. **Clone o repositório**:
    ```sh
    git clone git@github.com:brunomourasoares/projeto-csv-api.git
    cd projeto-csv-api
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

## Exemplo de Endpoints
- **Importar CSV**: `POST /api/data/import`

Usando postman, selecione tipo raw, e no campo abaixo, insira o caminho. Ex: D:/teste/lista.csv

- **Consultar Dados**: `GET /api/data/export?filePath=D:/teste/file.txt`

Pode ser usado tanto no navegador quanto no postman

## Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests.