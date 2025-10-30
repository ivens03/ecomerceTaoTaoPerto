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

## Documentação de Referência

Para mais referências, por favor, considere as seguintes seções (baseado nas suas dependências):

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
com.taotaoperto
├── config/                    # Configurações do projeto
├── usuarios/                  # Funcionalidades comuns a todos os usuários
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── model/
│   └── dto/
├── clientes/                  # Funcionalidades específicas para clientes
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── model/
│   └── dto/
├── vendedores/                # Funcionalidades específicas para vendedores
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── model/
│   └── dto/
│── entregadores/              # Funcionalidades específicas para os entregadores
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── model/
│   └── dto/
│── administradores/           # Funcionalidades administrativas
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── model/
│   └── dto/
│── suporte/                   # Funcionalidades suporte
    ├── controller/
    ├── service/
    ├── repository/
    ├── model/
    └── dto/
```

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