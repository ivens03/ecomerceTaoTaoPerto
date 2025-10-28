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

### 2. (Próximo ponto de melhoria)

- **Endpoint Afetado:**
- **Problema:**
- **Solução Sugerida:**