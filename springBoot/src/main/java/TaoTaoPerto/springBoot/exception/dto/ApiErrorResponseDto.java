package TaoTaoPerto.springBoot.exception.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErrorResponseDto {

    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    private Map<String, String> detalhes;

    public ApiErrorResponseDto(Instant timestamp, Integer status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.detalhes = null;
    }
}
