package TaoTaoPerto.springBoot.exception;

import TaoTaoPerto.springBoot.exception.dto.ApiErrorResponseDto;
import TaoTaoPerto.springBoot.exception.tratamentoDeErro.UsuarioDesativoOuNaoEncontrado;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

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
}
