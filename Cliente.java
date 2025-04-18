public interface Cliente {
    String obterNome();
    String obterIdentificador();
    String obterEndereco();
    void atualizarEndereco(String novoEndereco);
}
