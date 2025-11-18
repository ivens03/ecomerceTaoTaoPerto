package TaoTaoPerto.springBoot.exception.tratamentoDeErro;

import TaoTaoPerto.springBoot.usuarios.enums.TipoPessoaEnum;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import java.io.IOException;
import java.util.Arrays;

public class TipoPessoaDeserializer extends JsonDeserializer<TipoPessoaEnum> {

    @Override
    public TipoPessoaEnum deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String valorRecebido = p.getText();

        // 1. Tenta encontrar o enum ignorando Case (PF, pf, Pf...)
        for (TipoPessoaEnum tipo : TipoPessoaEnum.values()) {
            if (tipo.name().equalsIgnoreCase(valorRecebido)) {
                return tipo;
            }
        }

        // 2. Se não achou, lança a exceção do Jackson com a mensagem JÁ PRONTA
        // Isso tira a lógica de "montar mensagem" do GlobalExceptionHandler
        String mensagemErro = String.format(
                "O valor '%s' não é válido. Valores aceitos: %s",
                valorRecebido,
                Arrays.toString(TipoPessoaEnum.values())
        );

        throw new InvalidFormatException(
                p,
                mensagemErro,
                valorRecebido,
                TipoPessoaEnum.class
        );
    }

}
