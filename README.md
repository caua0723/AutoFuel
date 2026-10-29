<p align="center">
  <img src="banner/autofuel-banner.png" alt="Banner do AutoFuel" width="100%">
</p>

Sistema desktop acadêmico para gerenciamento das operações de um posto de combustível. O AutoFuel foi desenvolvido em Java Swing, com PostgreSQL para armazenamento dos dados.

## Funcionalidades

- Autenticação com perfis de acesso: administrador e operador
- Cadastro e consulta de funcionários
- Cadastro e consulta de bombas e combustíveis
- Cadastro de produtos e controle de estoque
- Registro e consulta de abastecimentos
- Registro de vendas da conveniência com seus respectivos itens
- Geração de relatórios com JasperReports

## Tecnologias utilizadas

- **Java 8:** linguagem utilizada no projeto
- **Java Swing:** criação das interfaces gráficas
- **PostgreSQL:** banco de dados relacional
- **JDBC:** conexão entre a aplicação Java e o banco de dados
- **pgcrypto:** geração e verificação de hashes de senha no banco de dados
- **JasperReports / iReport 5.6.0:** criação de relatórios
- **NetBeans e Apache Ant:** ambiente de desenvolvimento e ferramenta de build

## Estrutura do projeto

- `src/br/com/autofuel/ctr`: controladores, responsáveis por coordenar as ações da aplicação
- `src/br/com/autofuel/dao`: classes de acesso ao banco de dados
- `src/br/com/autofuel/dto`: objetos usados para transportar dados entre as camadas
- `src/br/com/autofuel/view`: telas e interfaces gráficas em Java Swing
- `src/br/com/autofuel/rels`: arquivos JRXML usados na definição dos relatórios
- `database/autofuel.sql`: script de criação e preenchimento do banco de demonstração
- `lib/`: bibliotecas Java utilizadas pelo projeto

## Como configurar e executar

### 1. Preparar o banco de dados

1. Instale o PostgreSQL 14 ou superior e abra o pgAdmin.
2. Crie um banco de dados vazio chamado `autofuel`.
3. Abra o **Query Tool** desse banco e execute o script `database/autofuel.sql`.
4. O script cria as tabelas, habilita a extensão `pgcrypto` e insere dados fictícios para demonstração.

**Atenção:** execute o script somente em um banco vazio. Ele foi preparado para configurar o banco de demonstração e não deve ser executado sobre um banco com dados que você deseja preservar.

### 2. Configurar a conexão

1. Na pasta raiz do projeto, copie o arquivo `config.properties.example` e renomeie a cópia para `config.properties`.
2. Abra `config.properties` e informe os dados da sua instalação do PostgreSQL: endereço, porta, nome do banco, usuário e senha.
3. O arquivo `config.properties` é ignorado pelo Git para evitar o envio das configurações locais ao repositório.

### 3. Abrir e executar o projeto

1. Abra o projeto no NetBeans usando o arquivo `build.xml`.
2. Confira se as bibliotecas necessárias estão disponíveis e se a conexão com o PostgreSQL está configurada corretamente.
3. Execute a aplicação pela classe `br.com.autofuel.view.LoginVIEW`.

O projeto utiliza bibliotecas legadas. Por isso, a compilação e a execução devem ser verificadas no ambiente em que ele for utilizado, especialmente no caso dos relatórios.

## Contas para demonstração

O script SQL cria duas contas fictícias:

| Perfil | Login | Senha |
|---|---|---|
| Administrador | `admin` | `AutoFuel@123` |
| Operador | `operador` | `Operador@123` |

Essas credenciais são públicas e servem apenas para testar o projeto. **Não as utilize em instalações reais.**

## Observações

- Os dados inseridos pelo script SQL são fictícios e servem para demonstração.
- O projeto foi desenvolvido para fins acadêmicos e utiliza tecnologias e bibliotecas legadas.
- A camada de acesso a dados (`DAO`) ainda precisa de uma revisão funcional e de segurança. Em especial, é recomendável revisar as consultas SQL, priorizar `PreparedStatement` e verificar o tratamento de erros e transações. Este repositório não deve ser considerado uma auditoria de segurança completa.

## Autor

**Cauã Augusto** — [GitHub](https://github.com/caua0723) · [LinkedIn](https://linkedin.com/in/cauã-augusto/)
