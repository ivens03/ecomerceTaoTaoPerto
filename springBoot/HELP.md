# Back-end SpringBoot

Este arquivo fornece ajuda para o back-end do projeto **E-commerce TaoTaoPerto**.

Este é o "Back-end do projeto feito para o MBA full stack com dev ops da unifametro". O objetivo é servir como a API RESTful que alimenta a lógica de negócios, o painel do vendedor, o checkout e a integração com os front-ends (Angular e Flutter).

## Especificações do Projeto

Informações-chave extraídas do seu `pom.xml`:

* **Java Version**: 21
* **Spring Boot Version (Parent)**: 3.5.6
* **Banco de Dados**: PostgreSQL (configurado no `pom.xml` como dependência de runtime)
* **Versão Específica (Spring Data JPA)**: 3.5.7 (Esta versão foi definida explicitamente no POM)

## Principais Dependências e Recursos

Seu projeto está configurado com as seguintes dependências principais do Spring Boot:

* **Spring Web** (`spring-boot-starter-web`): Essencial para construir as APIs RESTful que o seu E-commerce necessita (ex: endpoints para produtos, usuários, e o "Checkout Otimizado").
* **Spring Data JPA** (`spring-boot-starter-data-jpa`): Usado para a camada de persistência de dados. Facilita a comunicação com o banco de dados **PostgreSQL** para armazenar pedidos, usuários e o catálogo de produtos.
* **Validation** (`spring-boot-starter-validation`): Utilizado para validar os dados de entrada nas suas APIs (DTOs), crucial para garantir a integridade dos dados, como na "validação de idade" para o conteúdo adulto.
* **Springdoc OpenAPI (Swagger)** (`springdoc-openapi-starter-webmvc-ui`): Adicionado para gerar documentação de API interativa (Swagger UI) automaticamente a partir dos seus controllers.
* **Spring Boot DevTools** (`spring-boot-devtools`): Adicionado para habilitar o reinício automático (hot reload) durante o desenvolvimento, acelerando o ciclo de feedback a cada mudança no código.
* **Flyway Core** (`flyway-core`): Adicionado para gerenciamento de migrações de banco de dados (versionamento de schema), garantindo que a estrutura do banco de dados evolua de forma controlada.

## Documentação de Dependências Maven

Para mais dependências, por favor, considere as seguintes seções (baseado nas suas dependências):

* [Documentação Oficial do Apache Maven](https://maven.apache.org/guides/index.html)
* [Guia de Referência do Plugin Spring Boot Maven](https://docs.spring.io/spring-boot/3.5.6/maven-plugin)
* [Spring Web](https://docs.spring.io/spring-boot/3.5.6/reference/web/servlet.html) (Para suas APIs RESTful)
* [Spring Data JPA](https://docs.spring.io/spring-boot/3.5.7/reference/data/sql.html#data.sql.jpa-and-spring-data) (Para integração com PostgreSQL - link da versão 3.5.7)
* [Validation](https://docs.spring.io/spring-boot/3.5.6/reference/io/validation.html) (Para validar dados de entrada)
* **[Springdoc-openapi (Swagger)](https://springdoc.org/) (Documentação oficial da biblioteca de integração Swagger/OpenAPI)**
* **[Flyway Documentation](https://flywaydb.org/documentation/) (Documentação oficial do Flyway para migrações)**

## Guias Recomendados

Os seguintes guias ilustram como usar concretamente os recursos do seu projeto:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/) (Fundamental para seu back-end)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/) (Tutorial mais aprofundado sobre APIs REST)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/) (Relevante para seu uso do PostgreSQL)
* [Validation](https://spring.io/guides/gs/validating-form-input/) (Como aplicar as validações)
* **[Database Migrations with Flyway](https://spring.io/guides/gs/managing-database-migrations-with-flyway/) (Guia relevante do Spring para sua nova dependência)**

## Estrutura de Pacotes por Domínio de Usuário

O projeto segue uma arquitetura baseada em domínios, onde cada tipo de usuário do sistema possui seu próprio pacote com estrutura modular. Esta abordagem facilita a manutenção e escalabilidade do código.

### Estrutura de Diretórios

```
src/main/java/TaoTaoPerto/springBoot/
├── config/                    # Configurações do projeto
├── exception/                 # Tratamento de erros personalizados
│   ├── dto/
│   ├── tratamentoDeErro/
│   └── GlobalExceptionHandler
├── produtos/                  # Funcionalidades relacionadas a produtos
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── model/
│   └── dto/
└── usuarios/                  # Funcionalidades de usuários
    ├── controller/
    ├── service/
    ├── repository/
    ├── model/
    └── dto/
```

### Pacotes Principais

- **config/**: Configurações globais da aplicação
- **exception/**: Tratamento centralizado de exceções e erros
- **produtos/**: Lógica de negócios relacionada a produtos
- **usuarios/**: Gerenciamento de usuários e autenticação

> **Nota:** A estrutura está em desenvolvimento e novos módulos serão adicionados conforme a necessidade do projeto.

### Descrição dos Pacotes

1. **`usuarios/`**
   - Gerencia informações básicas de autenticação e perfil
   - Responsável pelo cadastro e login de todos os tipos de usuários
   - Mantém dados comuns como nome, e-mail, senha e perfil de acesso

2. **`clientes/`**
   - Gerencia funcionalidades específicas de clientes
   - Inclui carrinho de compras, histórico de pedidos e endereços
   - Contém regras de negócio específicas para compradores

3. **`vendedores/`**
   - Gerencia o catálogo de produtos
   - Controle de estoque e pedidos recebidos
   - Relatórios de vendas e desempenho

4. **`administradores/`**
   - Gerencia todo o ecossistema da plataforma
   - Controle de usuários e permissões
   - Relatórios administrativos e métricas do sistema

### Vantagens desta Estrutura

- **Separação clara de responsabilidades**: Cada tipo de usuário tem seu próprio espaço
- **Facilidade de manutenção**: Mudanças em um domínio não afetam os outros
- **Escalabilidade**: Novos tipos de usuários podem ser adicionados facilmente
- **Segurança**: Permite controle granular de permissões por domínio

### Exemplo de Fluxo

1. Um usuário se cadastra no sistema através do pacote `usuarios/`
2. Após o login, dependendo do perfil, ele é redirecionado para:
   - Cliente: `clientes/` para ver produtos e fazer compras
   - Vendedor: `vendedores/` para gerenciar produtos e pedidos
   - Administrador: `administradores/` para gerenciar o sistema

### Boas Práticas

- Cada pacote deve ser independente e ter baixo acoplamento
- A comunicação entre pacotes deve ser feita através de interfaces bem definidas
- Evite duplicação de código utilizando herança ou composição quando apropriado
- Mantenha a consistência na nomenclatura de classes e métodos

### Acesso à Documentação da API (Swagger)

Com a dependência `springdoc-openapi-starter-webmvc-ui` instalada, basta iniciar sua aplicação. A documentação interativa estará disponível em:

`http://localhost:8080/swagger-ui.html`

## Documentação Das APIs e DTOs

Descrição dos dados que a respectiva API deve receber e resultado esperado.

## 

- buscar
- cadastrar
- editar

## endpoint: /API/veiculos

```json
{
  "placa": "ABC-1234",
  "marca": "Toyota",
  "modelo": "Corolla",
  "ano": 2023
}
```

## Documentação do DB

Descrição dos schemas, tabelas, tipos e funções que compõem o banco de dados.

---

## Schemas

### Schema `usuarios`

Armazena todas as informações principais dos usuários da plataforma, incluindo dados de login, perfil, tipo de conta, endereços, preferências e perfis especializados (clientes, funcionários).

### Schema `auditoria`

Responsável por armazenar logs de auditoria e as funções de trigger relacionadas a eventos de segurança e modificações de dados.

### Schema `vendas`

Armazena dados específicos de vendedores (lojas), suas avaliações, perfis fiscais, regras de comissão e métricas de visualização.

### Schema `catalogo`

Contém a estrutura de produtos, categorias, imagens e avaliações de produtos.

### Schema `pedidos`

Responsável por armazenar os pedidos (carrinhos), os itens dos pedidos e a auditoria detalhada das comissões aplicadas.

### Schema `interacoes`

(V13) Schema dedicado ao rastreamento de eventos de navegação do cliente, como cliques, visualizações e pesquisas, para alimentar métricas de vendedor e sistemas de preferência do usuário.

---

## Tipos (Enums)

### usuarios.tipo_usuario_enum

(Definido em V2, usado em V10) Define os papéis (tipos) de usuário permitidos no sistema.

* `CLIENTE`
* `ENTREGADORES`
* `GERENTES`
* `SUPORTE`
* `VENDEDORES`

### pedidos.pedido_status_enum

(Definido em V8) Define os estados possíveis de um pedido durante seu ciclo de vida.

* `CARRINHO`
* `AGUARDANDO_PAGAMENTO`
* `PAGAMENTO_APROVADO`
* `PAGAMENTO_REJEITADO`
* `EM_SEPARACAO`
* `ENVIADO`
* `ENTREGUE`
* `CANCELADO`
* `REEMBOLSADO`

### pedidos.comissao_fonte_enum

(Definido em V8) Identifica a origem da regra de comissão aplicada a um item de pedido, para fins de auditoria.

* `PADRAO_VENDEDOR` (Usou a taxa de `vendas.perfis_vendedores`)
* `OVERRIDE_CATEGORIA` (Usou a taxa de `catalogo.categorias`)

### interacoes.interacao_tipo_enum

(Definido em V13) Define os tipos de eventos de navegação do cliente que podem ser rastreados.

* `VISUALIZACAO_PRODUTO`
* `CLICK_PRODUTO`
* `VISUALIZACAO_LOJA`
* `PESQUISA_TERMO`

### usuarios.tipo_moradia_enum

(Definido em V14) Diferencia o tipo de moradia para regras de negócio do endereço.

* `CASA`
* `CONDOMINIO`

---

## Tabelas

### Schema `usuarios`

#### Tabela `usuarios.usuarios`

Tabela principal que contém os registros de todos os usuários, seus dados pessoais, credenciais e status.

| Coluna | Tipo | Restrições | Descrição |
| :--- | :--- | :--- | :--- |
| `id` | BIGSERIAL | PRIMARY KEY | Identificador único (auto-incremento) do usuário. |
| `email` | VARCHAR(60) | NOT NULL, UNIQUE | Email de login do usuário. |
| `senha` | VARCHAR(255) | NOT NULL | Senha criptografada (hash) do usuário. |
| `nome_completo` | VARCHAR(255) | | Nome completo do usuário. |
| `cpf` | VARCHAR(11) | UNIQUE | CPF do usuário (único). |
| `celular` | VARCHAR(15) | UNIQUE | Número de celular do usuário (único). |
| `data_nascimento` | DATE | | Data de nascimento. |
| `avatar_url` | VARCHAR(500) | NULL | (V3) URL para a imagem de perfil (avatar) do usuário. |
| `tipo_usuario` | usuarios.tipo\_usuario\_enum | NOT NULL, DEFAULT 'CLIENTE' | (V2/V10) Papel do usuário no sistema. |
| `ativo` | BOOLEAN | NOT NULL, DEFAULT true | Indica se o usuário está ativo (`true`) ou inativo (`false`). |
| `criado_em` | TIMESTAMP | NOT NULL, DEFAULT CURRENT\_TIMESTAMP | Data e hora da criação do registro. |
| `atualizado_em` | TIMESTAMP | NOT NULL, DEFAULT CURRENT\_TIMESTAMP | Data e hora da última atualização (gerenciado pelo trigger `set_timestamp`). |
| `desativado_em` | TIMESTAMP | NULL | Data e hora da desativação da conta (se aplicável). |
| `ultimo_login_em` | TIMESTAMP | NULL | Data e hora do último login bem-sucedido. |
| `ultimo_login_ip` | VARCHAR(45) | NULL | Endereço IP do último login. |
| `tentativas_falhas_login` | INT | NOT NULL, DEFAULT 0 | Contador de falhas de login consecutivas (para bloqueio). |

#### Tabela `usuarios.enderecos`

(V3) Armazena os endereços associados a um usuário (relação 1-para-N).

| Coluna | Tipo | Restrições | Descrição |
| :--- | :--- | :--- | :--- |
| `id` | BIGSERIAL | PRIMARY KEY | Identificador único do endereço. |
| `usuario_id` | BIGINT | NOT NULL, FK (usuarios.usuarios) ON DELETE CASCADE | ID do usuário ao qual o endereço pertence. |
| `rotulo` | VARCHAR(50) | NOT NULL, DEFAULT 'CASA' | Rótulo do endereço (ex: 'CASA', 'TRABALHO'). |
| `cep` | VARCHAR(9) | NOT NULL | CEP (Código de Endereçamento Postal). |
| `logradouro` | VARCHAR(255) | NOT NULL | Nome da rua, avenida, etc. |
| `numero` | VARCHAR(20) | NOT NULL | (V13) Número do imóvel. |
| `complemento` | VARCHAR(100) | NULL (Condicional) | (V14) Complemento. Obrigatório se `tipo_moradia` = 'CONDOMINIO'. |
| `bairro` | VARCHAR(100) | NOT NULL | (V13) Nome do bairro. |
| `cidade` | VARCHAR(100) | NOT NULL | Nome da cidade. |
| `estado` | VARCHAR(2) | NOT NULL | (V4) Sigla do estado (UF) (ex: 'SP', 'CE'). |
| `tipo_moradia` | usuarios.tipo\_moradia\_enum | NOT NULL, DEFAULT 'CASA' | (V14) Diferencia se o endereço é CASA ou CONDOMINIO. |
| `ativo` | BOOLEAN | NOT NULL, DEFAULT true | (V5) Controle de "soft delete" (exclusão lógica). |
| `criado_em` | TIMESTAMP | NOT NULL, DEFAULT CURRENT\_TIMESTAMP | Data e hora da criação do registro. |
| `atualizado_em` | TIMESTAMP | NOT NULL, DEFAULT CURRENT\_TIMESTAMP | Data e hora da última atualização (gerenciado pelo trigger `set_timestamp`). |

*Constraints (V14):* `chk_complemento_obrigatorio_condominio` garante que `complemento` não seja nulo ou vazio se `tipo_moradia` = 'CONDOMINIO'.

#### Tabela `usuarios.configuracoes_seguranca`

(V3) Armazena dados sensíveis de segurança (relação 1-para-1 com `usuarios`).

| Coluna | Tipo | Restrições | Descrição |
| :--- | :--- | :--- | :--- |
| `id` | BIGSERIAL | PRIMARY KEY | Identificador único da configuração. |
| `usuario_id` | BIGINT | NOT NULL, UNIQUE, FK (usuarios.usuarios) ON DELETE CASCADE | ID do usuário (chave 1-para-1). |
| `mfa_habilitado` | BOOLEAN | NOT NULL, DEFAULT false | Indica se a autenticação de múltiplos fatores (2FA) está ativa. |
| `mfa_secret_key` | VARCHAR(255) | NULL | Chave secreta para apps autenticadores. |
| `recovery_codes` | TEXT | NULL | Códigos de recuperação (geralmente armazenados como hash). |
| `data_ultima_troca_senha` | TIMESTAMP | NOT NULL, DEFAULT CURRENT\_TIMESTAMP | Data da última alteração de senha (para expiração). |
| `atualizado_em` | TIMESTAMP | NOT NULL, DEFAULT CURRENT\_TIMESTAMP | Data e hora da última atualização (gerenciado pelo trigger `set_timestamp`). |

#### Tabela `usuarios.preferencias_usuario`

(V3) Armazena preferências de personalização e comunicação (relação 1-para-1 com `usuarios`).

| Coluna | Tipo | Restrições | Descrição |
| :--- | :--- | :--- | :--- |
| `id` | BIGSERIAL | PRIMARY KEY | Identificador único da preferência. |
| `usuario_id` | BIGINT | NOT NULL, UNIQUE, FK (usuarios.usuarios) ON DELETE CASCADE | ID do usuário (chave 1-para-1). |
| `idioma` | VARCHAR(10) | NOT NULL, DEFAULT 'pt-BR' | Idioma de preferência (ex: 'pt-BR', 'en-US'). |
| `fuso_horario` | VARCHAR(50) | NOT NULL, DEFAULT 'America/Sao_Paulo' | Fuso horário preferido pelo usuário. |
| `notificar_por_email` | BOOLEAN | NOT NULL, DEFAULT true | Permite notificações por e-mail. |
| `notificar_por_sms` | BOOLEAN | NOT NULL, DEFAULT false | Permite notificações por SMS. |
| `notificar_por_push` | BOOLEAN | NOT NULL, DEFAULT true | Permite notificações por Push (app mobile). |
| `atualizado_em` | TIMESTAMP | NOT NULL, DEFAULT CURRENT\_TIMESTAMP | Data e hora da última atualização (gerenciado pelo trigger `set_timestamp`). |

#### Tabela `usuarios.perfis_funcionarios`

(V3) Armazena dados *exclusivos* de usuários que são funcionários (relação 1-para-1 opcional com `usuarios`).

| Coluna | Tipo | Restrições | Descrição |
| :--- | :--- | :--- | :--- |
| `id` | BIGSERIAL | PRIMARY KEY | Identificador único do perfil. |
| `usuario_id` | BIGINT | NOT NULL, UNIQUE, FK (usuarios.usuarios) ON DELETE CASCADE | ID do usuário (chave 1-para-1). |
| `matricula` | VARCHAR(50) | UNIQUE | Matrícula interna do funcionário. |
| `cargo` | VARCHAR(100) | NOT NULL | (V13) Cargo (ex: 'Engenheiro de Software'). |
| `funcao` | VARCHAR(255) | NOT NULL, DEFAULT 'Função não definida' | (V13) Descrição detalhada das responsabilidades ou função do cargo. |
| `departamento` | VARCHAR(100) | NOT NULL | (V13) Departamento (ex: 'Tecnologia'). |
| `gestor_id` | BIGINT | NULL, FK (usuarios.usuarios) ON DELETE SET NULL | ID do usuário que é o gestor direto deste funcionário. |
| `data_admissao` | DATE | NOT NULL | (V13) Data de admissão do funcionário. |
| `data_desligamento` | DATE | NULL | Data de desligamento (se aplicável). |
| `criado_em` | TIMESTAMP | NOT NULL, DEFAULT CURRENT\_TIMESTAMP | Data e hora da criação do registro. |
| `atualizado_em` | TIMESTAMP | NOT NULL, DEFAULT CURRENT\_TIMESTAMP | Data e hora da última atualização (gerenciado pelo trigger `set_timestamp`). |

#### Tabela `usuarios.perfis_clientes`

(V9) Armazena dados *exclusivos* de usuários que são clientes (relação 1-para-1 opcional com `usuarios`).

| Coluna | Tipo | Restrições | Descrição |
| :--- | :--- | :--- | :--- |
| `id` | BIGSERIAL | PRIMARY KEY | Identificador único do perfil. |
| `usuario_id` | BIGINT | NOT NULL, UNIQUE, FK (usuarios.usuarios) ON DELETE CASCADE | ID do usuário (chave 1-para-1). |
| `pontos_fidelidade` | BIGINT | NOT NULL, DEFAULT 0 | Pontos de fidelidade acumulados. |
| `codigo_indicacao` | VARCHAR(20) | UNIQUE | Código de indicação deste cliente (ex: 'IVENS-123'). |
| `endereco_padrao_id` | BIGINT | NULL, FK (usuarios.enderecos) ON DELETE SET NULL | ID do endereço preferido para checkout. |
| `criado_em` | TIMESTAMP | NOT NULL, DEFAULT CURRENT\_TIMESTAMP | Data e hora da criação do registro. |
| `atualizado_em` | TIMESTAMP | NOT NULL, DEFAULT CURRENT\_TIMESTAMP | Data e hora da última atualização (gerenciado pelo trigger `set_timestamp`). |

---
#### Tabela `usuarios.perfis_entregadores`

(V15) Armazena dados *exclusivos* de usuários que são entregadores (relação 1-para-1 obrigatória se tipo = ENTREGADORES).

| Coluna | Tipo | Restrições | Descrição |
| :--- | :--- | :--- | :--- |
| `usuario_id` | BIGINT | PRIMARY KEY, FK (usuarios.usuarios) ON DELETE CASCADE | ID do usuário. Serve como PK e FK simultaneamente (Shared PK). |
| `placa_veiculo` | VARCHAR(10) | UNIQUE | Placa do veículo utilizado para entregas. |
| `cnh_categoria` | VARCHAR(5) | | Categoria da CNH (ex: 'A', 'B', 'AB'). |
| `em_entrega` | BOOLEAN | DEFAULT FALSE | Indica se o entregador está em uma rota ativa no momento. |

#### Tabela `usuarios.perfis_gerentes`

(V15) Armazena dados *exclusivos* de usuários que são gerentes (relação 1-para-1 obrigatória se tipo = GERENTES).

| Coluna | Tipo | Restrições | Descrição |
| :--- | :--- | :--- | :--- |
| `usuario_id` | BIGINT | PRIMARY KEY, FK (usuarios.usuarios) ON DELETE CASCADE | ID do usuário. Serve como PK e FK simultaneamente (Shared PK). |
| `matricula` | VARCHAR(20) | UNIQUE | Matrícula funcional do gerente. |
| `data_admissao` | DATE | | Data de admissão na empresa. |
| `nivel_acesso` | INTEGER | | Nível numérico de permissão administrativa. |

### Schema `auditoria`

#### Tabela `auditoria.log_auditoria_usuarios`

(V2) Registra eventos importantes de auditoria relacionados à tabela `usuarios` (ex: login, falha de login, atualização de perfil).

| Coluna | Tipo | Restrições | Descrição |
| :--- | :--- | :--- | :--- |
| `id` | BIGSERIAL | PRIMARY KEY | Identificador único do log. |
| `usuario_id` | BIGINT | FK (usuarios.usuarios) ON DELETE SET NULL | O ID do usuário que **sofreu** a ação (o registro que foi modificado). |
| `ator_usuario_id` | BIGINT | FK (usuarios.usuarios) | O ID do usuário que **executou** a ação (o "ator"). |
| `acao` | VARCHAR(50) | NOT NULL | Tipo de ação (ex: 'LOGIN_SUCESSO', 'UPDATE_PERFIL'). |
| `ip_acao` | VARCHAR(45) | NOT NULL | Endereço IP de onde a ação se originou. |
| `detalhes_antes` | TEXT | | Estado do registro (JSON) antes da modificação. |
| `detalhes_depois` | TEXT | | Estado do registro (JSON) depois da modificação. |
| `timestamp_acao` | TIMESTAMP | NOT NULL, DEFAULT CURRENT\_TIMESTAMP | Data e hora exata em que a ação ocorreu. |

---

### Schema `vendas`

#### Tabela `vendas.perfis_vendedores`

(V6 original, Refatorado na V15) Armazena os dados da "loja" de um usuário do tipo `VENDEDOR`.
**Nota V15:** A estrutura foi alterada para usar o `usuario_id` como Chave Primária (Shared PK), removendo a antiga coluna `id` auto-incremento.

| Coluna | Tipo | Restrições | Descrição |
| :--- | :--- | :--- | :--- |
| `usuario_id` | BIGINT | PRIMARY KEY, FK (usuarios.usuarios) ON DELETE CASCADE | ID do usuário. Serve como PK e FK simultaneamente (Shared PK). |
| `nome_loja` | VARCHAR(150) | NOT NULL, UNIQUE | Nome público da loja/vendedor. |
| `descricao` | TEXT | NULL | Descrição pública da loja. |
| `logo_url` | VARCHAR(500) | NULL | URL do logo da loja. |
| `banner_url` | VARCHAR(500) | NULL | URL do banner da loja. |
| `nota_media` | DECIMAL(3, 2) | NOT NULL, DEFAULT 0.00 | Média de notas (calculada a partir das avaliações). |
| `total_avaliacoes` | INT | NOT NULL, DEFAULT 0 | Contagem total de avaliações recebidas. |
| `tipo_pessoa` | VARCHAR(255) | NOT NULL | (V11) Tipo de pessoa: 'PF' ou 'PJ'. |
| `razao_social` | VARCHAR(255) | NULL | (V11) Razão Social (obrigatório se PJ). |
| `cnpj` | VARCHAR(14) | NULL, UNIQUE | (V11) CNPJ (obrigatório se PJ). |
| `inscricao_estadual` | VARCHAR(20) | NULL | (V11) Inscrição Estadual (opcional). |
| `criado_em` | TIMESTAMP | NOT NULL, DEFAULT CURRENT_TIMESTAMP | Data e hora da criação do registro. |
| `atualizado_em` | TIMESTAMP | NOT NULL, DEFAULT CURRENT_TIMESTAMP | Data e hora da última atualização. |

*Constraints (V11):* `chk_dados_pj_obrigatorios` garante que `razao_social` e `cnpj` não sejam nulos se `tipo_pessoa` = 'PJ'.

#### Tabela `vendas.avaliacoes_vendedores`

(V6) Armazena as avaliações feitas por clientes sobre os vendedores (lojas).

| Coluna | Tipo | Restrições | Descrição |
| :--- | :--- | :--- | :--- |
| `id` | BIGSERIAL | PRIMARY KEY | Identificador único da avaliação. |
| `vendedor_perfil_id` | BIGINT | NOT NULL, FK (vendas.perfis\_vendedores) ON DELETE CASCADE | ID do vendedor (loja) que está sendo avaliado. |
| `cliente_usuario_id` | BIGINT | NOT NULL, FK (usuarios.usuarios) ON DELETE SET NULL | ID do cliente que fez a avaliação. |
| `nota` | SMALLINT | NOT NULL, CHECK (nota >= 1 AND nota <= 5) | Nota de 1 a 5. |
| `titulo` | VARCHAR(100) | NULL | Título da avaliação. |
| `comentario` | TEXT | NULL | Texto da avaliação. |
| `resposta_vendedor` | TEXT | NULL | Resposta opcional do vendedor. |
| `data_resposta` | TIMESTAMP | NULL | Data da resposta do vendedor. |
| `criado_em` | TIMESTAMP | NOT NULL, DEFAULT CURRENT\_TIMESTAMP | Data e hora da criação do registro. |
| `atualizado_em` | TIMESTAMP | NOT NULL, DEFAULT CURRENT\_TIMESTAMP | Data e hora da última atualização (gerenciado pelo trigger `set_timestamp`). |

---

### Schema `catalogo`

#### Tabela `catalogo.categorias`

(V7) Armazena as categorias para organização dos produtos (estrutura em árvore).

| Coluna | Tipo | Restrições | Descrição |
| :--- | :--- | :--- | :--- |
| `id` | BIGSERIAL | PRIMARY KEY | Identificador único da categoria. |
| `nome` | VARCHAR(100) | NOT NULL, UNIQUE | Nome da categoria. |
| `descricao` | TEXT | NULL | Descrição da categoria. |
| `categoria_pai_id` | BIGINT | NULL, FK (catalogo.categorias) ON DELETE SET NULL | ID da categoria pai (para subcategorias). |
| `comissao_percentual_override` | DECIMAL(5, 2) | NULL | (V8) Taxa de comissão específica para esta categoria (sobrepõe a do vendedor). |
| `ativo` | BOOLEAN | NOT NULL, DEFAULT TRUE | (V12) Se a categoria está ativa e visível. |
| `conteudo_adulto` | BOOLEAN | NOT NULL, DEFAULT FALSE | (V13) Se TRUE, indica que a categoria é de conteúdo adulto. |
| `criado_em` | TIMESTAMP | NOT NULL, DEFAULT CURRENT\_TIMESTAMP | Data e hora da criação do registro. |
| `atualizado_em` | TIMESTAMP | NOT NULL, DEFAULT CURRENT\_TIMESTAMP | Data e hora da última atualização (gerenciado pelo trigger `set_timestamp`). |

#### Tabela `catalogo.produtos`

(V7) Tabela principal de produtos cadastrados pelos vendedores.

| Coluna | Tipo | Restrições | Descrição |
| :--- | :--- | :--- | :--- |
| `id` | BIGSERIAL | PRIMARY KEY | Identificador único do produto. |
| `vendedor_perfil_id` | BIGINT | NOT NULL, FK (vendas.perfis\_vendedores) ON DELETE CASCADE | ID do vendedor (loja) dono do produto. |
| `nome` | VARCHAR(255) | NOT NULL | Nome do produto. |
| `descricao` | TEXT | NULL | Descrição detalhada do produto. |
| `sku` | VARCHAR(100) | UNIQUE NOT NULL | SKU (Stock Keeping Unit) do produto. |
| `preco_base` | DECIMAL(10, 2) | NOT NULL | Preço original do produto. |
| `preco_promocional` | DECIMAL(10, 2) | NULL | Preço com desconto (se aplicável). |
| `estoque_quantidade` | INT | NOT NULL, DEFAULT 0 | Quantidade disponível em estoque. |
| `peso_gramas` | INT | NULL | Peso do produto em gramas (para frete). |
| `dimensoes_json` | TEXT | NULL | JSON com dimensões (altura\_cm, largura\_cm, etc). |
| `ativo` | BOOLEAN | NOT NULL, DEFAULT true | Se o produto está visível e disponível para venda. |
| `conteudo_adulto` | BOOLEAN | NOT NULL, DEFAULT false | Marcador de conteúdo adulto. |
| `impulsionado` | BOOLEAN | NOT NULL, DEFAULT false | Marcador para destaque em buscas (marketing). |
| `nota_media` | DECIMAL(3, 2) | NOT NULL, DEFAULT 0.00 | Média de notas (calculada das avaliações). |
| `total_avaliacoes` | INT | NOT NULL, DEFAULT 0 | Contagem total de avaliações do produto. |
| `criado_em` | TIMESTAMP | NOT NULL, DEFAULT CURRENT\_TIMESTAMP | Data e hora da criação do registro. |
| `atualizado_em` | TIMESTAMP | NOT NULL, DEFAULT CURRENT\_TIMESTAMP | Data e hora da última atualização (gerenciado pelo trigger `set_timestamp`). |

#### Tabela `catalogo.produtos_categorias`

(V7) Tabela de junção (N-para-N) entre produtos e categorias.

| Coluna | Tipo | Restrições | Descrição |
| :--- | :--- | :--- | :--- |
| `produto_id` | BIGINT | NOT NULL, FK (catalogo.produtos) ON DELETE CASCADE | ID do produto. |
| `categoria_id` | BIGINT | NOT NULL, FK (catalogo.categorias) ON DELETE CASCADE | ID da categoria. |
| | | PRIMARY KEY (produto\_id, categoria\_id) | |

#### Tabela `catalogo.imagens_produtos`

(V7) Armazena as URLs das imagens de um produto (relação 1-para-N).

| Coluna | Tipo | Restrições | Descrição |
| :--- | :--- | :--- | :--- |
| `id` | BIGSERIAL | PRIMARY KEY | Identificador único da imagem. |
| `produto_id` | BIGINT | NOT NULL, FK (catalogo.produtos) ON DELETE CASCADE | ID do produto ao qual a imagem pertence. |
| `imagem_url` | VARCHAR(500) | NOT NULL | URL da imagem. |
| `alt_text` | VARCHAR(150) | | Texto alternativo (acessibilidade). |
| `ordem` | SMALLINT | NOT NULL, DEFAULT 0 | Ordem de exibição (0 = principal). |

#### Tabela `catalogo.avaliacoes_produtos`

(V7) Armazena as avaliações feitas por clientes sobre produtos específicos.

| Coluna | Tipo | Restrições | Descrição |
| :--- | :--- | :--- | :--- |
| `id` | BIGSERIAL | PRIMARY KEY | Identificador único da avaliação. |
| `produto_id` | BIGINT | NOT NULL, FK (catalogo.produtos) ON DELETE CASCADE | ID do produto avaliado. |
| `cliente_usuario_id` | BIGINT | NOT NULL, FK (usuarios.usuarios) ON DELETE SET NULL | ID do cliente que fez a avaliação. |
| `nota` | SMALLINT | NOT NULL, CHECK (nota >= 1 AND nota <= 5) | Nota de 1 a 5. |
| `titulo` | VARCHAR(100) | NULL | Título da avaliação. |
| `comentario` | TEXT | NULL | Texto da avaliação. |
| `criado_em` | TIMESTAMP | NOT NULL, DEFAULT CURRENT\_TIMESTAMP | Data e hora da criação do registro. |
| `atualizado_em` | TIMESTAMP | NOT NULL, DEFAULT CURRENT\_TIMESTAMP | Data e hora da última atualização (gerenciado pelo trigger `set_timestamp`). |

---

### Schema `pedidos`

#### Tabela `pedidos.pedidos`

(V8) Tabela principal do pedido (Header), agregando os valores totais.

| Coluna | Tipo | Restrições | Descrição |
| :--- | :--- | :--- | :--- |
| `id` | BIGSERIAL | PRIMARY KEY | Identificador único do pedido. |
| `cliente_usuario_id` | BIGINT | NOT NULL, FK (usuarios.usuarios) ON DELETE RESTRICT | ID do cliente que fez o pedido. |
| `endereco_entrega_id` | BIGINT | NULL, FK (usuarios.enderecos) ON DELETE RESTRICT | ID do endereço de entrega selecionado. |
| `status` | pedidos.pedido\_status\_enum | NOT NULL, DEFAULT 'AGUARDANDO\_PAGAMENTO' | Status atual do pedido (ex: 'PAGAMENTO\_APROVADO'). |
| `valor_produtos` | DECIMAL(10, 2) | NOT NULL | Soma do valor dos produtos. |
| `valor_frete` | DECIMAL(10, 2) | NOT NULL, DEFAULT 0.00 | Custo do frete. |
| `valor_desconto` | DECIMAL(10, 2) | NOT NULL, DEFAULT 0.00 | Valor de descontos/cupons aplicados. |
| `valor_total` | DECIMAL(10, 2) | NOT NULL | Valor final (produtos + frete - desconto). |
| `detalhes_pagamento_json` | TEXT | NULL | JSON com dados da transação de pagamento. |
| `criado_em` | TIMESTAMP | NOT NULL, DEFAULT CURRENT\_TIMESTAMP | Data e hora da criação do registro. |
| `atualizado_em` | TIMESTAMP | NOT NULL, DEFAULT CURRENT\_TIMESTAMP | Data e hora da última atualização (gerenciado pelo trigger `set_timestamp`). |

#### Tabela `pedidos.pedido_itens`

(V8) Armazena os itens de um pedido e audita a comissão aplicada em cada um.

| Coluna | Tipo | Restrições | Descrição |
| :--- | :--- | :--- | :--- |
| `id` | BIGSERIAL | PRIMARY KEY | Identificador único do item. |
| `pedido_id` | BIGINT | NOT NULL, FK (pedidos.pedidos) ON DELETE CASCADE | ID do pedido ao qual o item pertence. |
| `vendedor_perfil_id` | BIGINT | NOT NULL, FK (vendas.perfis\_vendedores) ON DELETE RESTRICT | ID do vendedor do produto. |
| `produto_id` | BIGINT | NOT NULL, FK (catalogo.produtos) ON DELETE RESTRICT | ID do produto vendido. |
| `nome_produto` | VARCHAR(255) | NOT NULL | Nome do produto (snapshot do momento da compra). |
| `quantidade` | INT | NOT NULL, CHECK (quantidade > 0) | Quantidade comprada. |
| `preco_unitario_venda` | DECIMAL(10, 2) | NOT NULL | Preço unitário (snapshot do momento da compra). |
| `valor_bruto_itens` | DECIMAL(10, 2) | NOT NULL | (Quantidade * Preço Unitário). |
| `percentual_comissao_aplicado` | DECIMAL(5, 2) | NOT NULL | (Auditoria) % de comissão que foi usada (ex: 12.50). |
| `valor_comissao_plataforma` | DECIMAL(10, 2) | NOT NULL | (Auditoria) Valor final (R$) da comissão da plataforma. |
| `valor_liquido_vendedor` | DECIMAL(10, 2) | NOT NULL | (Auditoria) Valor líquido (Bruto - Comissão) do vendedor. |
| `fonte_comissao` | pedidos.comissao\_fonte\_enum | NOT NULL | (Auditoria) De onde veio a regra de comissão (Padrão ou Categoria). |

---

### Schema `interacoes`

#### Tabela `interacoes.log_interacao_cliente`

(V13) Registra eventos de navegação do cliente (cliques, visualizações, pesquisas) para alimentar métricas e preferências.

| Coluna | Tipo | Restrições | Descrição |
| :--- | :--- | :--- | :--- |
| `id` | BIGSERIAL | PRIMARY KEY | Identificador único do log de interação. |
| `usuario_id` | BIGINT | NOT NULL, FK (usuarios.usuarios) ON DELETE CASCADE | ID do usuário que realizou a ação. |
| `produto_id` | BIGINT | NULL, FK (catalogo.produtos) ON DELETE SET NULL | O produto que foi visto ou clicado. |
| `vendedor_perfil_id` | BIGINT | NULL, FK (vendas.perfis\_vendedores) ON DELETE SET NULL | A loja que foi vista (ou a loja dona do produto visto). |
| `tipo_interacao` | interacoes.interacao\_tipo\_enum | NOT NULL | O tipo de interação (ex: 'VISUALIZACAO\_PRODUTO', 'PESQUISA\_TERMO'). |
| `termo_pesquisa` | VARCHAR(255) | NULL | O termo pesquisado, se `tipo_interacao` = 'PESQUISA\_TERMO'. |
| `timestamp_interacao` | TIMESTAMP | NOT NULL, DEFAULT CURRENT\_TIMESTAMP | Data e hora exata da interação. |

---

## Funções e Triggers

### Schema `auditoria`

#### Função: `auditoria.trigger_set_timestamp()`

* **Tipo:** Função de Trigger (Linguagem `plpgsql`).
* **Propósito:** (V2) Esta função é projetada para ser disparada por um trigger. Sua única responsabilidade é definir o valor da coluna `atualizado_em` do registro (`NEW`) para o timestamp atual (`NOW()`).

#### Trigger: `set_timestamp`

* **Tabelas Alvo:**
    * `usuarios.usuarios` (V2)
    * `usuarios.enderecos` (V3)
    * `usuarios.configuracoes_seguranca` (V3)
    * `usuarios.preferencias_usuario` (V3)
    * `usuarios.perfis_funcionarios` (V3)
    * `vendas.perfis_vendedores` (V6)
    * `vendas.avaliacoes_vendedores` (V6)
    * `catalogo.categorias` (V7)
    * `catalogo.produtos` (V7)
    * `catalogo.avaliacoes_produtos` (V7)
    * `pedidos.pedidos` (V8)
    * `usuarios.perfis_clientes` (V9)
* **Evento:** `BEFORE UPDATE`
* **Ação:** Para cada linha (`FOR EACH ROW`) que está sendo atualizada nas tabelas alvo, executa a função `auditoria.trigger_set_timestamp()`.
* **Resultado:** Garante que a coluna `atualizado_em` seja sempre atualizada automaticamente.

---

### Schema `usuarios`

#### Função: `usuarios.trigger_criar_perfis_satelite_v2()`

* **Tipo:** Função de Trigger (Linguagem `plpgsql`).
* **Propósito:** (V9, substituindo a função da V3) Disparada automaticamente após um novo usuário ser criado. Ela inicializa os registros 1-para-1 obrigatórios e/ou baseados em tipo.
* **Ação:**
    1.  Insere um registro padrão em `usuarios.configuracoes_seguranca`.
    2.  Insere um registro padrão em `usuarios.preferencias_usuario`.
    3.  **SE** `NEW.tipo_usuario` for `'CLIENTE'`, também insere um registro padrão em `usuarios.perfis_clientes`.

#### Trigger: `criar_perfis_satelite_v2_apos_insert_usuario`

* **Tabela Alvo:** `usuarios.usuarios`
* **Evento:** `AFTER INSERT`
* **Ação:** (V9, substituindo o trigger da V3) Para cada nova linha (`FOR EACH ROW`), executa a função `usuarios.trigger_criar_perfis_satelite_v2()`.
* **Resultado:** Garante que todo novo usuário tenha seus perfis satélite (segurança, preferências e, se aplicável, cliente) criados automaticamente.