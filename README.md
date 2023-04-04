# Lista 29 - Projeto Spring : Sistema Funcionários
- Desenvolvido no curso DEV-TI (Unoesc)

Este projeto é um sistema simples para gerenciar funcionários. O objetivo é criar um sistema onde o usuário possa visualizar, adicionar, editar e excluir funcionários do banco de dados.

### Tecnologias Utilizadas

O projeto utiliza as seguintes tecnologias:

- `STS (Spring Tool Suit 4)` como ambiente de desenvolvimento
- `Maven` para gerenciamento de dependências
- Linguagem `Java`
- `HTML` e `Thymeleaf` como motor de template
- `CSS` e `Bootstrap` para estilização
- `JavaScript` e `JQuery` para interações do lado do cliente
- `PostgreSQL` como banco de dados relacional
- `JPA Hibernate` como framework de persistência
- `Lombok` para reduzir boilerplate
- Documentação `Swagger` para documentar a API
- `H2 Database` para testes
- `EasyAutoComplete` para autocompletar dados
- `Open Iconic`, para estilizar o sistema com ícones

### Como executar o projeto

Para executar o projeto é necessário seguir os seguintes passos:

> **Warning**: É importante que você tenha o PostgreSQL e seus drivers instalados

1. Clone o repositório em sua máquina local utilizando o comando abaixo:

   ```shell
   git clone https://github.com/mart-sc/lista29-sistema-funcionarios.git
   ```

2. Abra o STS e importe o projeto utilizando o menu "File > Import > General > Projects from Folder or Archive"
3. Configure as credenciais do banco de dados `PostgreSQL` no arquivo `application-dev.properties`:
	- Por padrão está configurado **Username:** `postgres` e **Password:** `postgres`.
	- Também será necessário configurar a URL da sua conexão com o postgreSQL adicionando seu banco de dados na url: `jdbc:postgresql://localhost:5432/{database}`
4. Execute o projeto clicando com o botão direito sobre a classe principal, ou na pasta do projeto, depois em "Run As > Spring Boot App"
5. Acesse o sistema no navegador utilizando o endereço `http://localhost:8080`

### Documentação da API

A documentação da API pode ser acessada em http://localhost:8080/swagger-ui.html.
   