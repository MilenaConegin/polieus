Integrantes 

Isabelle Ferreira Neri Feitoza | RM571265 
Marina Fernandes Gomes Mesquita | RM573507 
Milena Silva Conegin | RM568923 
Murilo Almeida Rodrigues de Souza | RM573977 
Henrique Bonachela de Carvalho Carabante | RM573620 

Link do Vídeo:
https://youtube.com/shorts/jx9nrLeJG2s?is=qqZvxbMN7glMS1kn 

Link da API:  
https://polieus.onrender.com 

Como executar o projeto Polieus

1. Pré-requisitos

Antes de iniciar, é necessário possuir as seguintes ferramentas instaladas:

Java JDK 21: ambiente de execução da aplicação.

IntelliJ IDEA: IDE utilizada para abrir e executar o projeto.

Maven: ferramenta responsável pelo gerenciamento das dependências.

Oracle Database: banco de dados utilizado para armazenar as informações.

Insomnia: ferramenta opcional para testar os endpoints da API.

2. Baixar o projeto

Acesse o repositório do projeto no GitHub: https://github.com/MilenaConegin/polieus

Clique no botão Code.

Selecione Download ZIP.

Extraia o arquivo baixado em uma pasta do computador.

3. Abrir o projeto no IntelliJ

Abra o IntelliJ IDEA.

Selecione a opção Open.

Localize a pasta onde o projeto foi extraído.

Selecione a pasta principal polieus.

Aguarde o IntelliJ reconhecer o projeto Maven e baixar as dependências necessárias.

4. Configurar o banco de dados

O projeto utiliza o Oracle Database para armazenar os dados.

Antes de executar a aplicação, é necessário:

Ter acesso a uma instância do Oracle Database.

Criar as tabelas necessárias para o funcionamento do sistema.

Configurar as informações de conexão por meio das seguintes variáveis de ambiente:

Variável

Descrição

DB_URL

Endereço de conexão com o banco Oracle.

DB_USER

Usuário do banco de dados.

DB_PASSWORD

Senha do banco de dados.

Essas variáveis são utilizadas pela classe ConnectionFactory para estabelecer a conexão com o banco.

5. Configurar as variáveis de ambiente

No Windows, as variáveis podem ser configuradas no PowerShell antes de executar a aplicação:

$env:DB_URL="ENDERECO_DO_ORACLE"
$env:DB_USER="USUARIO_DO_BANCO"
$env:DB_PASSWORD="SENHA_DO_BANCO"

Substitua os valores de exemplo pelas informações reais de conexão com o Oracle Database.

6. Executar a aplicação

Após configurar o ambiente:

No IntelliJ, acesse o pacote br.com.fiap.

Localize a classe PolieusApplication.java.

Clique no botão de execução (ícone verde) ao lado do método main.

Selecione Run 'PolieusApplication'.

Aguarde a inicialização do Spring Boot.

7. Testando a API pelo Insomnia
Após a aplicação estar publicada no Render, é possível realizar testes utilizando o Insomnia, ferramenta que permite enviar requisições HTTP e visualizar as respostas da API.

URL base da aplicação:
https://polieus.onrender.com

7.1 Endpoints de Usuário
Operação	Método	Rota completa
Cadastro	POST	https://polieus.onrender.com/polieus/usuario
Listar todos	GET	https://polieus.onrender.com/polieus/usuario
Buscar por ID	GET	https://polieus.onrender.com/polieus/usuario/1
Atualizar	PUT	https://polieus.onrender.com/polieus/usuario/2
Excluir	DELETE	https://polieus.onrender.com/polieus/usuario/2
Login	POST	https://polieus.onrender.com/polieus/usuario/login

7.2 Endpoints de Administrativo
Operação	Método	Rota completa
Cadastro	POST	https://polieus.onrender.com/polieus/administrativo
Login	POST	https://polieus.onrender.com/polieus/administrativo/login
Listar todos	GET	https://polieus.onrender.com/polieus/administrativo
Buscar por ID	GET	https://polieus.onrender.com/polieus/administrativo/2
Atualizar	PUT	https://polieus.onrender.com/polieus/administrativo/3
Excluir	DELETE	https://polieus.onrender.com/polieus/administrativo/3

7.3 Endpoints de Ocorrência
Operação	Método	Rota completa
Cadastrar	POST	https://polieus.onrender.com/polieus/ocorrencia
Listar todos	GET	https://polieus.onrender.com/polieus/ocorrencia
Buscar por ID	GET	https://polieus.onrender.com/polieus/ocorrencia/3
Buscar por usuário	GET	https://polieus.onrender.com/polieus/ocorrencia/usuario/1
Atualizar	PUT	https://polieus.onrender.com/polieus/ocorrencia/1
Excluir	DELETE	https://polieus.onrender.com/polieus/ocorrencia/1

7.4 Endpoints de Histórico
Operação	Método	Rota completa
Cadastrar	POST	https://polieus.onrender.com/polieus/historico
Listar todos	GET	https://polieus.onrender.com/polieus/historico
Buscar por ID	GET	https://polieus.onrender.com/polieus/historico/21
Buscar por ocorrência	GET	https://polieus.onrender.com/polieus/historico/ocorrencia/1
Atualizar	PUT	https://polieus.onrender.com/polieus/historico/21
Atualizar status	PUT	https://polieus.onrender.com/polieus/historico/ocorrencia/1/status
Excluir	DELETE	https://polieus.onrender.com/polieus/historico/22

7.5 Endpoints de Análise
Operação	Método	Rota completa
Cadastrar	POST	https://polieus.onrender.com/polieus/analise
Listar todos	GET	https://polieus.onrender.com/polieus/analise
Buscar por ID	GET	https://polieus.onrender.com/polieus/analise/1
Atualizar	PUT	https://polieus.onrender.com/polieus/analise/2
Excluir	DELETE	https://polieus.onrender.com/polieus/analise/2

7.6 Endpoints de Relatório
Operação	Método	Rota completa
Cadastrar	POST	https://polieus.onrender.com/polieus/relatorio
Listar todos	GET	https://polieus.onrender.com/polieus/relatorio
Buscar por ID	GET	https://polieus.onrender.com/polieus/relatorio/1
Atualizar	PUT	https://polieus.onrender.com/polieus/relatorio/3
Excluir	DELETE	https://polieus.onrender.com/polieus/relatorio/3
Quando a aplicação iniciar corretamente, será possível visualizar no console a mensagem de inicialização do servidor.

7.7 Como realizar os testes no Insomnia
1. Criar uma requisição

Abra o Insomnia, crie uma nova requisição HTTP e escolha o método desejado.
2. Informar a rota

Copie a URL completa do endpoint e cole no campo de endereço da requisição.
3. Configurar o JSON

Para POST e PUT, acesse Body, selecione JSON e insira os dados necessários.

4. Executar e verificar

Clique em Send e confira o código HTTP e o conteúdo retornado pela API.


Exemplos de JSON de entrada e saída 

Usuário save(Post)  
Endpoint: /polieus/usuario 

URL: https://polieus.onrender.com/polieus/usuario 


 Entrada — JSON 
{ 
  "nomeUsuario": "Maria Silva", 
  "cpf": "12345678901", 
  "telefone": "11987654321", 
  "email": "maria@email.com", 
  "senha": "Maria@123" 
} 

Saída — HTTP 201 Created 
{ 
  "idUsuario": 1, 
  "nomeUsuario": "Maria Silva", 
  "cpf": "12345678901", 
  "telefone": "11987654321", 
  "email": "maria@email.com", 
  "senha": "Maria@123" 
} 

7.8 Observações importantes
- POST: utilizado para cadastrar novos registros e realizar login.
- GET: utilizado para consultar registros.
- PUT: utilizado para atualizar informações existentes.
- DELETE: utilizado para excluir registros.
- Nas requisições POST e PUT que recebem dados, utilize o formato JSON.
- Nas rotas que possuem identificadores, substitua os números de exemplo pelo ID do registro desejado.
- Os códigos HTTP retornados devem corresponder aos resultados esperados na tabela de endpoints.

