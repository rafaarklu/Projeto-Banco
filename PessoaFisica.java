public class PessoaFisica implements Cliente {
    private String nomeCompleto;
    private String cpf;
    private String localizacao;

    public PessoaFisica(String nome, String cpf, String endereco) {
        this.nomeCompleto = nome;
        this.cpf = cpf;
        this.localizacao = endereco;
    }

    @Override
    public String obterNome() {
        return nomeCompleto;
    }

    @Override
    public String obterIdentificador() {
        return cpf;
    }

    @Override
    public String obterEndereco() {
        return localizacao;
    }

    @Override
    public void atualizarEndereco(String novoEndereco) {
        this.localizacao = novoEndereco;
    }

    public String getCpf() {
        return cpf;
    }
}
