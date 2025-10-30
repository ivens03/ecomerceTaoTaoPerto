package TaoTaoPerto.springBoot.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiErrorResponseDto {
    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
