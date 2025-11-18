package TaoTaoPerto.springBoot.exception;

import TaoTaoPerto.springBoot.exception.dto.ApiErrorResponseDto;
import TaoTaoPerto.springBoot.exception.tratamentoDeErro.UsuarioDesativoOuNaoEncontrado;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Este método captura a exceção 'UsuarioDesativoOuNaoEncontrado'
     * que você está lançando no seu Service.
     */
    @ExceptionHandler(UsuarioDesativoOuNaoEncontrado.class)
    public ResponseEntity<ApiErrorResponseDto> handleUsuarioNaoEncontrado(
            UsuarioDesativoOuNaoEncontrado ex,
            HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiErrorResponseDto errorResponse = new ApiErrorResponseDto(
                Instant.now(),
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(errorResponse);
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiErrorResponseDto> handleDataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request) {

        String mensagemErro = "Erro de integridade de dados.";
        String detalhe = ex.getRootCause() != null ? ex.getRootCause().getMessage() : ex.getMessage();

        if (detalhe != null) {
            if (detalhe.contains("usuarios_email_key")) {
                mensagemErro = "Este endereço de e-mail já está cadastrado em nossa base.";
            } else if (detalhe.contains("usuarios_cpf_key")) {
                mensagemErro = "Este CPF já está cadastrado.";
            } else if (detalhe.contains("usuarios_celular_key")) {
                mensagemErro = "Este número de celular já está em uso.";
            } else {
                mensagemErro = "Operação violou uma restrição de unicidade no banco de dados.";
            }
        }

        ApiErrorResponseDto errorResponse = new ApiErrorResponseDto(
                Instant.now(),
                HttpStatus.CONFLICT.value(),
                "Conflito de Dados",
                mensagemErro,
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponseDto> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {

        Map<String, String> errosValidacao = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String nomeCampo = ((FieldError) error).getField();
            String mensagemErro = error.getDefaultMessage();
            errosValidacao.put(nomeCampo, mensagemErro);
        });

        ApiErrorResponseDto errorResponse = new ApiErrorResponseDto(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Dados Inválidos",
                "Erro de validação. Verifique os campos abaixo.",
                request.getRequestURI(),
                errosValidacao
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponseDto> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, HttpServletRequest request) {

        String mensagem = "Erro no formato do JSON enviado.";
        Map<String, String> detalhes = new HashMap<>();

        // Se a causa for o nosso erro de formato (gerado pelo Deserializer)
        if (ex.getCause() instanceof com.fasterxml.jackson.databind.exc.InvalidFormatException ifx) {

            // Pega o nome do campo (ex: "tipoPessoa")
            String nomeCampo = ifx.getPath().isEmpty() ? "desconhecido" : ifx.getPath().get(ifx.getPath().size() - 1).getFieldName();

            // AQUI ESTÁ A LIMPEZA: A mensagem bonita já vem pronta do ifx.getOriginalMessage()
            // que nós definimos no TipoPessoaDeserializer!
            detalhes.put(nomeCampo, ifx.getOriginalMessage());
            mensagem = "Valor inválido fornecido.";
        }

        ApiErrorResponseDto errorResponse = new ApiErrorResponseDto(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Formato Inválido",
                mensagem,
                request.getRequestURI(),
                detalhes.isEmpty() ? null : detalhes
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
