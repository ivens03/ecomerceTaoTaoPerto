package TaoTaoPerto.springBoot.exception.tratamentoDeErro;

public class UsuarioDesativoOuNaoEncontrado extends RuntimeException {
  public UsuarioDesativoOuNaoEncontrado(String message) {
    super(message);
  }
}
