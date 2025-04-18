import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Banco {
    private final List<Cliente> registrosClientes;
    private final List<Conta> registrosContas;
    private final Scanner input;

    public Banco() {
        this.registrosClientes = new ArrayList<>();
        this.registrosContas = new ArrayList<>();
        this.input = new Scanner(System.in);
    }

    private void adicionarCliente() {
        System.out.println("\n--- Novo Cadastro ---");
        System.out.println("1 - PF (Pessoa Física)");
        System.out.println("2 - PJ (Pessoa Jurídica)");
        System.out.print("Opção: ");
        int tipo = input.nextInt();
        input.nextLine(); // limpar buffer

        System.out.print("Nome: ");
        String nome = input.nextLine();
        System.out.print("Endereço: ");
        String endereco = input.nextLine();

        if (tipo == 1) {
            System.out.print("CPF: ");
            String cpf = input.nextLine();
            registrosClientes.add(new PessoaFisica(nome, cpf, endereco));
        } else if (tipo == 2) {
            System.out.print("CNPJ: ");
            String cnpj = input.nextLine();
            registrosClientes.add(new PessoaJuridica(nome, cnpj, endereco));
        } else {
            System.out.println("Tipo inválido.");
            return;
        }

        System.out.println("Cadastro efetuado com sucesso!");
    }

    private void criarConta() {
        if (registrosClientes.isEmpty()) {
            System.out.println("Nenhum cliente disponível para vincular a conta.");
            return;
        }

        System.out.println("\n--- Cadastro de Conta ---");
        System.out.println("1 - Corrente");
        System.out.println("2 - Poupança");
        System.out.println("3 - Rendimento");
        System.out.print("Tipo: ");
        int tipo = input.nextInt();
        input.nextLine();

        System.out.println("\nSelecione o cliente:");
        for (int i = 0; i < registrosClientes.size(); i++) {
            System.out.println((i + 1) + " - " + registrosClientes.get(i).obterNome());
        }
        System.out.print("Número: ");
        int indiceCliente = input.nextInt() - 1;
        input.nextLine();

        if (indiceCliente < 0 || indiceCliente >= registrosClientes.size()) {
            System.out.println("Cliente inválido!");
            return;
        }

        Cliente titular = registrosClientes.get(indiceCliente);
        System.out.print("Número da conta: ");
        String numeroConta = input.nextLine();

        Conta novaConta;
        switch (tipo) {
            case 1:
                System.out.print("Limite da conta (0 se não quiser): ");
                double limite = input.nextDouble();
                input.nextLine();
                novaConta = (limite > 0)
                        ? new ContaCorrente(numeroConta, titular, limite)
                        : new ContaCorrente(numeroConta, titular);
                break;
            case 2:
                novaConta = new ContaPoupanca(numeroConta, titular);
                break;
            case 3:
                novaConta = new ContaRendimento(numeroConta, titular);
                break;
            default:
                System.out.println("Tipo inválido.");
                return;
        }

        registrosContas.add(novaConta);
        System.out.println("Conta criada com sucesso!");
    }

    private void executarOperacao() {
        if (registrosContas.isEmpty()) {
            System.out.println("Não há contas para realizar operações.");
            return;
        }

        System.out.println("\n--- Operações Disponíveis ---");
        System.out.println("1 - Depositar");
        System.out.println("2 - Sacar");
        System.out.print("Escolha: ");
        int acao = input.nextInt();
        input.nextLine();

        System.out.println("\nContas cadastradas:");
        for (int i = 0; i < registrosContas.size(); i++) {
            System.out.println((i + 1) + " - " + registrosContas.get(i));
        }

        System.out.print("Conta escolhida: ");
        int indiceConta = input.nextInt() - 1;

        if (indiceConta < 0 || indiceConta >= registrosContas.size()) {
            System.out.println("Conta não localizada.");
            return;
        }

        System.out.print("Valor: ");
        double valor = input.nextDouble();

        Conta contaSelecionada = registrosContas.get(indiceConta);
        if (acao == 1) {
            contaSelecionada.depositar(valor);
        } else if (acao == 2) {
            contaSelecionada.sacar(valor);
        } else {
            System.out.println("Operação inválida!");
        }
    }

    private void mostrarResumo() {
        System.out.println("\n--- Resumo de Clientes e Contas ---");
        for (Cliente cliente : registrosClientes) {
            System.out.println("\nCliente: " + cliente.obterNome());
            System.out.println("Identificação: " + cliente.obterIdentificador());
            System.out.println("Endereço: " + cliente.obterEndereco());
            System.out.println("Contas vinculadas:");
            for (Conta conta : registrosContas) {
                if (conta.getCliente().equals(cliente)) {
                    System.out.println("  - " + conta);
                }
            }
        }
    }

    private void excluirCliente() {
        if (registrosClientes.isEmpty()) {
            System.out.println("Nenhum cliente para remover.");
            return;
        }

        System.out.println("\n--- Exclusão de Cliente ---");
        for (int i = 0; i < registrosClientes.size(); i++) {
            System.out.println((i + 1) + " - " + registrosClientes.get(i).obterNome());
        }
        System.out.print("Escolha: ");
        int pos = input.nextInt() - 1;

        if (pos < 0 || pos >= registrosClientes.size()) {
            System.out.println("Cliente inválido!");
            return;
        }

        Cliente aRemover = registrosClientes.get(pos);
        registrosContas.removeIf(conta -> conta.getCliente().equals(aRemover));
        registrosClientes.remove(pos);
        System.out.println("Cliente e suas contas foram removidos.");
    }

    public void iniciarSistema() {
        while (true) {
            System.out.println("\n=== Sistema do Banco ===");
            System.out.println("1 - Novo Cliente");
            System.out.println("2 - Nova Conta");
            System.out.println("3 - Operações");
            System.out.println("4 - Exibir Todos");
            System.out.println("5 - Deletar Cliente");
            System.out.println("0 - Finalizar");
            System.out.print("Opção: ");
            int escolha = input.nextInt();
            input.nextLine();

            switch (escolha) {
                case 1:
                    adicionarCliente();
                    break;
                case 2:
                    criarConta();
                    break;
                case 3:
                    executarOperacao();
                    break;
                case 4:
                    mostrarResumo();
                    break;
                case 5:
                    excluirCliente();
                    break;
                case 0:
                    System.out.println("Encerrando o sistema. Até logo!");
                    return;
                default:
                    System.out.println("Opção não reconhecida.");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        new Banco().iniciarSistema();
    }
}
