# Projeto Contracts

## Descrição

Este projeto é composto por um backend em Java com Spring localizado na pasta `back-contracts` e um frontend em ReactJS localizado na pasta `front-contracts`. Ele consome a API [iPify](https://api.ipify.org/?format=json).

## Estrutura do Projeto

- **back-contracts**: Backend Java com Spring.
- **front-contracts**: Frontend ReactJS.

## Pré-requisitos

Antes de iniciar, certifique-se de ter o seguinte instalado:
- [Java 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html) ou superior.
- [Gradle](https://gradle.org/install/) (para o backend).
- [Node.js](https://nodejs.org/) (inclui o npm) (para o frontend).
- [MySQL](https://dev.mysql.com/downloads/mysql/) (se estiver usando um banco de dados MySQL).

## Configuração do Backend

1. **Navegue até a pasta do backend**:

    ```bash
    cd path/to/back-contracts
    ```

2. **Configure o banco de dados**:
   - Certifique-se de que o MySQL está em execução.
   - Atualize as configurações de banco de dados no arquivo `src/main/resources/application.properties` (ou `application.yml`, dependendo da configuração):

     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     ```

3. **Construa o projeto**:

    ```bash
    ./gradlew build
    ```

4. **Execute o servidor**:

    ```bash
    ./gradlew bootRun
    ```

   O backend estará disponível em `http://localhost:8080`.

## Configuração do Frontend

1. **Navegue até a pasta do frontend**:

    ```bash
    cd path/to/front-contracts
    ```

2. **Instale as dependências**:

    ```bash
    npm install
    ```

3. **Inicie o servidor de desenvolvimento**:

    ```bash
    npm start
    ```

   O frontend estará disponível em `http://localhost:3000`.

## Como Contribuir

1. **Faça um fork do repositório**.
2. **Crie uma nova branch para sua feature**:

    ```bash
    git checkout -b nome-da-sua-branch
    ```

3. **Faça suas alterações e adicione commits**:

    ```bash
    git add .
    git commit -m "Descrição das alterações"
    ```

4. **Envie a branch para o repositório remoto**:

    ```bash
    git push origin nome-da-sua-branch
    ```

5. **Abra um Pull Request no repositório principal**.

## Testes

Para executar os testes do backend, use o comando:

```bash
./gradlew test
