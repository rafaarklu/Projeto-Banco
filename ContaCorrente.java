public class ContaCorrente extends Conta {
    public ContaCorrente(String numero, Cliente dono) {
        super(numero, dono);
    }

    public ContaCorrente(String numero, Cliente dono, double limite) {
        super(numero, dono);
        this.valorLimite = limite;
    }

    @Override
    public void depositar(double valor) {
        if (valor > 0) {
            valorAtual += valor;
            System.out.println("Depósito efetuado!");
        } else {
            System.out.println("Valor inválido.");
        }
    }

    @Override
    public boolean sacar(double valor) {
        if (valor > 0 && valor <= (valorAtual + valorLimite)) {
            valorAtual -= valor;
            System.out.println("Saque realizado.");
            return true;
        } else {
            System.out.println("Saldo insuficiente.");
            return false;
        }
    }

    @Override
    public void aplicarRendimento() {
        
    }
}
