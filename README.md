# AutoFuel ⛽

Sistema desktop acadêmico para gerenciamento de operações de um posto de combustível, desenvolvido em Java Swing com PostgreSQL.

## Funcionalidades

- Login com perfis de acesso
- Cadastro e consulta de funcionários
- Cadastro e consulta de bombas e combustíveis
- Cadastro de produtos e controle de estoque
- Registro e consulta de abastecimentos
- Vendas da conveniência com itens por venda
- Relatórios com JasperReports

## Tecnologias

- Java (projeto legado configurado para compatibilidade de linguagem Java 8)
- Java Swing
- PostgreSQL e JDBC
- `pgcrypto` para hash de senha no banco
- JasperReports/iReport 5.6.0
- NetBeans e Ant

## Estrutura

- `src/br/com/autofuel/ctr`: controladores
- `src/br/com/autofuel/dao`: acesso a dados
- `src/br/com/autofuel/dto`: objetos de transferência de dados
- `src/br/com/autofuel/view`: interfaces Swing
- `src/br/com/autofuel/rels`: fontes JRXML dos relatórios
- `database/autofuel.sql`: estrutura e dados fictícios para demonstração
- `lib/`: bibliotecas Java necessárias ao projeto

## Configuração do banco

1. Instale o PostgreSQL e o pgAdmin.
2. Crie um banco vazio chamado `autofuel`.
3. Abra o Query Tool desse banco e execute `database/autofuel.sql`.
4. Copie `config.properties.example` para `config.properties` na pasta raiz do projeto.
5. Edite `config.properties` com o host, a porta, o usuário e a senha do seu PostgreSQL. Esse arquivo é ignorado pelo Git.
6. Abra o projeto pelo `build.xml` no NetBeans e execute `br.com.autofuel.view.LoginVIEW`.

Também é possível configurar por variáveis de ambiente: `AUTOFUEL_DB_HOST`, `AUTOFUEL_DB_PORT`, `AUTOFUEL_DB_NAME`, `AUTOFUEL_DB_USER` e `AUTOFUEL_DB_PASSWORD`. Propriedades Java `autofuel.db.host`, `autofuel.db.port`, `autofuel.db.database`, `autofuel.db.user` e `autofuel.db.password` também são aceitas.

### Contas de demonstração

- Administrador: `admin` / `AutoFuel@123`
- Operador: `operador` / `Operador@123`

Essas credenciais são públicas e servem apenas para demonstração. Não as utilize em instalações reais.

## Observações

- O script SQL é destinado a um banco vazio; não o execute sobre um banco que contenha dados que você deseja preservar.
- O projeto utiliza bibliotecas legadas. O funcionamento dos relatórios pode variar conforme a versão do Java instalada; a compilação e a execução devem ser verificadas no ambiente de destino.
- As operações DAO originais ainda precisam de uma revisão funcional mais ampla para substituir concatenações SQL por `PreparedStatement` e validar tratamento de erros/transações. Não considere esta versão uma auditoria de segurança completa.
- Projeto acadêmico individual desenvolvido no contexto do curso de Sistemas de Informação do IFSP.

## Autor

Cauã Augusto — [GitHub](https://github.com/caua0723) · [LinkedIn](https://linkedin.com/in/cauã-augusto/)
