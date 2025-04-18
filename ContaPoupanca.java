public class ContaPoupanca extends Conta {
    private static final double TAXA = 0.005;

    public ContaPoupanca(String numero, Cliente titular) {
        super(numero, titular);
    }

    @Override
    public void depositar(double valor) {
        if (valor > 0) {
            valorAtual += valor;
            System.out.println("Valor depositado com sucesso.");
        } else {
            System.out.println("Depósito inválido.");
        }
    }

    @Override
    public boolean sacar(double valor) {
        if (valor > 0 && valor <= valorAtual) {
            valorAtual -= valor;
            System.out.println("Saque efetuado.");
            return true;
        } else {
            System.out.println("Saldo insuficiente.");
            return false;
        }
    }

    @Override
    public void aplicarRendimento() {
        double ganho = valorAtual * TAXA;
        valorAtual += ganho;
        System.out.println("Rendimento de R$ " + String.format("%.2f", ganho) + " aplicado.");
    }
}
