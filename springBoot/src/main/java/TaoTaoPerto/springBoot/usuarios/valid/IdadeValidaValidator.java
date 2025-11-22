package TaoTaoPerto.springBoot.usuarios.valid;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

public class IdadeValidaValidator implements ConstraintValidator<IdadeValida, LocalDate> {

    private int idadeMinima;
    private int idadeMaxima;

    @Override
    public void initialize(IdadeValida constraintAnnotation) {
        this.idadeMinima = constraintAnnotation.idadeMinima();
        this.idadeMaxima = constraintAnnotation.idadeMaxima();
    }

    @Override
    public boolean isValid(LocalDate dataNascimento, ConstraintValidatorContext context) {

        if (dataNascimento == null) {
            return true;
        }

        LocalDate hoje = LocalDate.now();

        if (dataNascimento.isAfter(hoje)) {
            return false;
        }

        Period periodo = Period.between(dataNascimento, hoje);
        int idade = periodo.getYears();

        return idade >= idadeMinima && idade <= idadeMaxima;
    }
}
