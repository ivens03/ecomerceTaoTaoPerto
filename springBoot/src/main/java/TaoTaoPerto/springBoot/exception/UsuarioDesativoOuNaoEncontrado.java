package TaoTaoPerto.springBoot.exception;

public class UsuarioDesativoOuNaoEncontrado extends RuntimeException {
  public UsuarioDesativoOuNaoEncontrado(String message) {
    super(message);
  }
}
