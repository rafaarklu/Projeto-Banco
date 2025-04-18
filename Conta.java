public abstract class Conta {
    protected String idConta;
    protected Cliente clienteResponsavel;
    protected double valorAtual;
    protected double valorLimite;

    public Conta(String idConta, Cliente cliente) {
        this.idConta = idConta;
        this.clienteResponsavel = cliente;
        this.valorAtual = 0.0;
        this.valorLimite = 0.0;
    }

    public abstract void depositar(double valor);
    public abstract boolean sacar(double valor);
    public abstract void aplicarRendimento();

    public String getIdConta() {
        return idConta;
    }

    public Cliente getCliente() {
        return clienteResponsavel;
    }

    public double getSaldo() {
        return valorAtual;
    }

    public double getLimite() {
        return valorLimite;
    }

    @Override
    public String toString() {
        return "Conta [" +
                "número='" + idConta + '\'' +
                ", cliente=" + clienteResponsavel.obterNome() +
                ", saldo=R$ " + String.format("%.2f", valorAtual) +
                ", limite=R$ " + String.format("%.2f", valorLimite) +
                ']';
    }
}
