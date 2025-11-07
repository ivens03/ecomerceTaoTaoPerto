# Pontos de Melhoria do Projeto

Este arquivo documenta tarefas de refatoração, correção de bugs e melhorias gerais pendentes no sistema.

### 1. Implementar Validações de DTOs com Bean Validation

- **Problema:** Os DTOs (`UsuarioDto`, `EnderecoDto`) não possuem validações automáticas de entrada. Isso força a camada de serviço a validar dados (ex: verificar se um campo é nulo ou vazio) ou, pior, permite que dados inválidos cheguem ao banco.
- **Análise:** Sem validação na entrada (Controller), o sistema pode receber, por exemplo, um `UsuarioDto` com nome em branco ou um `EnderecoDto` sem CEP. Isso pode gerar erros `500` no banco (constraint violations) ou salvar dados "sujos".
- **Solução Sugerida:**
    1.  **Adicionar Dependência:** Incluir `spring-boot-starter-validation` no `pom.xml` (se ainda não estiver presente).
    2.  **Anotar os DTOs:** Usar anotações do `jakarta.validation.constraints` (ex: `@NotEmpty`, `@Size`, `@Email`) nos campos dos DTOs.
        - *Exemplo (`UsuarioDto.java`):*
          ```java
          public class UsuarioDto {
              @NotEmpty(message = "O nome completo não pode ser vazio")
              @Size(min = 3, max = 100)
              private String nomeCompleto;

              @NotEmpty(message = "A senha não pode ser vazia")
              private String senha;
              // ... outros campos
          }
          ```

    3.  **Ativar Validação no Controller:** Adicionar a anotação `@Valid` antes do `@RequestBody` nos métodos `POST` e `PUT` (ex: no `UsuarioController` que recebe o `salvarUsuario`).
    4.  **Capturar Erros de Validação:** No *Handler* Global (`@RestControllerAdvice` criado no Ponto 2), adicionar um método para capturar `MethodArgumentNotValidException`. Este método deve retornar `HttpStatus.BAD_REQUEST (400)` e uma lista com todos os erros de validação (ex: "O nome completo não pode ser vazio").

## Testes Automatizados

### 2. Implementar Cobertura de Testes Automatizados

- **Problema:** O projeto não possui cobertura de testes automatizados (unitários ou de integração).
- **Análise:** A ausência de testes torna o processo de refatoração (como as melhorias 1, 2 e 3) arriscado, pois não há como garantir que as mudanças não introduziram bugs (regressões) em outras partes do código. Isso compromete a confiabilidade da aplicação e aumenta o custo de manutenção a longo prazo.
- **Solução Sugerida:**
    1.  **Adicionar Dependência:** Garantir que o `spring-boot-starter-test` está no `pom.xml`. Ele inclui JUnit 5, Mockito e Spring Test.
    2.  **Testes Unitários (Camada de Serviço):**
        * **Onde:** Criar classes de teste para `UsuarioServices` e `EnderecoServices`.
        * **Como:** Usar `@ExtendWith(MockitoExtension.class)` e `@Mock` para simular o comportamento dos repositórios.
        * **O que testar:**
            * **Caminho Feliz:** Verificar se os métodos retornam os resultados esperados.
            * **Tratamento de Erros:** Garantir que as exceções corretas são lançadas.

    3.  **Testes de Integração (API REST):**
        * **Onde:** Criar testes para os endpoints dos controladores.
        * **Como:** Usar `@SpringBootTest` e `TestRestTemplate`.
        * **O que testar:**
            * **Status HTTP:** Verificar códigos de resposta.
            * **Corpo da Resposta:** Validar o JSON retornado.

### 3. Segurança Avançada
-  Implementar autenticação JWT
-  Configurar autorização baseada em roles
-  Proteger endpoints sensíveis

### 4. Logging Estratégico
-  Adicionar logs em pontos críticos
  ```java
  @Slf4j
  @Service
  public class UsuarioService {
      public UsuarioDto buscarPorId(Long id) {
          log.info("Buscando usuário com ID: {}", id);
          // implementação
      }
  }
  ```

### 5. Cache de Dados
-  Implementar cache para consultas frequentes
  ```java
  @Service
  @CacheConfig(cacheNames = "usuarios")
  public class UsuarioService {
      @Cacheable
      public UsuarioDto buscarPorId(Long id) {
          // implementação
      }
  }
  ```

### 6. Monitoramento
-  Adicionar Spring Boot Actuator
  ```xml
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-actuator</artifactId>
  </dependency>
  ```

### 7. Melhoria de mensagens de erro

- Adiconar um tratamento de erros nos services que não tem
```
    - Categorias de produtos
    - Produtos
```