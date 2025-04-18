public class PessoaJuridica implements Cliente {
    private String razaoSocial;
    private String cnpj;
    private String enderecoEmpresa;

    public PessoaJuridica(String nome, String cnpj, String endereco) {
        this.razaoSocial = nome;
        this.cnpj = cnpj;
        this.enderecoEmpresa = endereco;
    }

    @Override
    public String obterNome() {
        return razaoSocial;
    }

    @Override
    public String obterIdentificador() {
        return cnpj;
    }

    @Override
    public String obterEndereco() {
        return enderecoEmpresa;
    }

    @Override
    public void atualizarEndereco(String novoEndereco) {
        this.enderecoEmpresa = novoEndereco;
    }

    public String getCnpj() {
        return cnpj;
    }
}
