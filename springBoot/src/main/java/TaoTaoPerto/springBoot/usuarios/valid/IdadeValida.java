package TaoTaoPerto.springBoot.usuarios.valid;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = IdadeValidaValidator.class)
@Target({ FIELD })
@Retention(RUNTIME)
public @interface IdadeValida {

    int idadeMinima() default 18;

    int idadeMaxima() default 120;

    String message() default "A data de nascimento é inválida. O usuário deve ter entre {idadeMinima} e {idadeMaxima} anos e não pode ser uma data futura.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}