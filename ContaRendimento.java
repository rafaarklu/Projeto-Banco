public class ContaRendimento extends Conta {
    private static final double INDICE_RENDIMENTO = 0.01;

    public ContaRendimento(String numero, Cliente cliente) {
        super(numero, cliente);
    }

    @Override
    public void depositar(double valor) {
        if (valor > 0) {
            valorAtual += valor;
            System.out.println("Depósito realizado com sucesso.");
        } else {
            System.out.println("Valor inválido para depósito.");
        }
    }

    @Override
    public boolean sacar(double valor) {
        System.out.println("Operação negada: Conta de rendimento não permite saques.");
        return false;
    }

    @Override
    public void aplicarRendimento() {
        double retorno = valorAtual * INDICE_RENDIMENTO;
        valorAtual += retorno;
        System.out.println("Rendimento aplicado: R$ " + String.format("%.2f", retorno));
    }
}
