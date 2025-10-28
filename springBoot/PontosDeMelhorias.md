# Pontos de Melhoria do Projeto

Este arquivo documenta tarefas de refatoração, correção de bugs e melhorias gerais pendentes no sistema.

## API - Tratamento de Erros

### 1. Corrigir resposta de erro ao buscar usuários inativos

- **Endpoint Afetado:** `GET /gerentes/dados/usuarios/listarPorIdAtivo/{id}`
- **Problema:** Atualmente, ao tentar buscar um usuário passando um ID que existe no banco de dados, mas que não está com o status "ativo", a API retorna um erro `500 (Internal Server Error)`.
- **Análise:** Um erro 500 indica uma falha inesperada no servidor (provavelmente uma `NullPointerException` ou `NoResultException` não tratada). Este não é o status HTTP correto para um recurso que não atende aos critérios da busca.
- **Solução Sugerida:**
    1.  Modificar a camada de serviço (Service) ou controlador (Controller) para tratar o caso em que o usuário não é encontrado ou não está ativo.
    2.  Em vez de permitir que a exceção estoure, a API deve retornar um status `404 (Not Found)`.
    3.  Incluir no corpo da resposta uma mensagem de erro clara para o cliente, informando o motivo.

- **Exemplo de Resposta (JSON):**
    ```json
    {
      "status": 404,
      "mensagem": "Usuário não encontrado ou inativo."
    }
    ```


### 2. Centralizar Tratamento de Erros com Exceções Customizadas

- **Problema:** A camada de serviço está lançando exceções genéricas (`RuntimeException`) para regras de negócio e falhas de busca (ex: "recurso não encontrado").
- **Análise:** Lançar `RuntimeException` diretamente dos serviços faz com que o Spring trate qualquer falha de negócio como um `500 (Internal Server Error)`, o que é semanticamente incorreto. O Ponto 1 desta lista é um sintoma direto deste problema. A lógica de tratamento de erro está espalhada e misturada com a lógica de negócio.
- **Exemplos no Código Atual:**
    - Em `UsuarioServices.java` (método `atualizarUsuario`):
      ```java
      UsuarioModel usuarioModel = usuarioRepository.findById(id)
              .orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));
      ```

    - Em `EnderecoServices.java` (método `deletLogicoEndereco`):
      ```java
      EnderecoModel enderecoModel = enderecoRepository.findById(id)
              .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
      ```

    - Em `UsuarioServices.java` (método `listarUsuarioPorId`):
      ```java
      if (buscadorDeUsuarioPorID.isEmpty()) {
          throw new RuntimeException("Usuario com ID: " + id + " não foi encontrado");
      }
      ```

- **Solução Sugerida:**
    1.  **Criar Exceções Específicas:** Definir classes de exceção customizadas (ex: `RecursoNaoEncontradoException extends RuntimeException`).
    2.  **Refatorar os Services:** Substituir os `RuntimeException` pelas novas exceções.
        - *Exemplo de mudança no `UsuarioServices`*:
          ```java
          // Antes
          // .orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));
          // Depois
          .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário com ID: " + id + " não encontrado"));
          ```

    3.  **Implementar um Handler Global (`@RestControllerAdvice`):** Criar uma classe `RestExceptionHandler` que intercepta exceções.
    4.  **Mapear Exceções para Status HTTP:** Dentro do *Handler*, usar `@ExceptionHandler(RecursoNaoEncontradoException.class)` para capturar a exceção e retornar um `ResponseEntity` com `HttpStatus.NOT_FOUND (404)` e um corpo de erro padronizado (como o sugerido no Ponto 1).

### 3. Implementar Validações de DTOs com Bean Validation

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

### 4. Implementar Cobertura de Testes Automatizados

- **Problema:** O projeto não possui cobertura de testes automatizados (unitários ou de integração).
- **Análise:** A ausência de testes torna o processo de refatoração (como as melhorias 1, 2 e 3) arriscado, pois não há como garantir que as mudanças não introduziram bugs (regressões) em outras partes do código. Isso compromete a confiabilidade da aplicação e aumenta o custo de manutenção a longo prazo.
- **Solução Sugerida:**
    1.  **Adicionar Dependência:** Garantir que o `spring-boot-starter-test` está no `pom.xml`. Ele inclui JUnit 5, Mockito e Spring Test.
    2.  **Testes Unitários (Camada de Serviço):**
        * **Onde:** Criar classes de teste para `UsuarioServices` e `EnderecoServices`.
        * **Como:** Usar `@ExtendWith(MockitoExtension.class)` e `@Mock` para simular o comportamento dos *Repositories* (ex: `UsuarioRepository`, `EnderecoRepository`).
        * **O que testar:**
            * **Caminho Feliz:** Testar se `salvarUsuario` chama `repository.save()` e retorna o DTO correto.
            * **Tratamento de Erros:** Testar se os métodos (ex: `listarUsuarioPorIdAtivo`) lançam a `RecursoNaoEncontradoException` (sugerida no Ponto 2) quando o *repository* retorna um `Optional.empty()` ou um usuário inativo.
            * **Lógica de Negócio:** Testar se `buscarTodosAtivos` filtra corretamente os usuários inativos.
    3.  **Testes de Integração (Camada de Controller):**
        * **Onde:** Criar classes de teste para `UsuarioConsultasController` e `EnderecoController`.
        * **Como:** Usar `@SpringBootTest` e `@AutoConfigureMockMvc` para testar os endpoints da API sem levantar um servidor real.
        * **O que testar:**
            * **Status HTTP:** Verificar se os endpoints retornam os status corretos (ex: `201 CREATED` para `POST /endereco/registro`, `404 NOT FOUND` para `GET /endereco/listar/999` (ID inexistente)).
            * **Validação (Ponto 3):** Testar se, ao enviar um DTO inválido (ex: nome em branco) para um endpoint `POST` ou `PUT`, a API retorna `400 BAD REQUEST`.
            * **Respostas:** Verificar se o JSON de resposta está correto e contém os dados esperados.