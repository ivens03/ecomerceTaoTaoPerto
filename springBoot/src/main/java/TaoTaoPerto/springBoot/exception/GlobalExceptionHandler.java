package TaoTaoPerto.springBoot.exception;

import TaoTaoPerto.springBoot.exception.dto.ApiErrorResponseDto;
import TaoTaoPerto.springBoot.exception.exception.UsuarioDesativoOuNaoEncontrado;
import jakarta.servlet.http.HttpServletRequest;
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
}
