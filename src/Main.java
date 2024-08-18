import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Criação do cliente
        String nomeCliente;
        while (true) {
            System.out.print("Digite o nome do cliente: ");
            nomeCliente = scanner.nextLine();
            if (nomeCliente.matches("[A-Za-z ]+")) {
                break;
            } else {
                System.out.println("Nome inválido. O nome não pode conter números. Tente novamente.");
            }
        }

        String cpfCliente;
        while (true) {
            System.out.print("Digite o CPF do cliente (11 dígitos): ");
            cpfCliente = scanner.nextLine();
            if (cpfCliente.matches("\\d{11}")) {
                break;
            } else {
                System.out.println("CPF inválido. O CPF deve conter exatamente 11 dígitos. Tente novamente.");
            }
        }

        Cliente cliente = new Cliente();
        cliente.setNome(nomeCliente);
        cliente.setCpf(cpfCliente);

        // Escolha do tipo de conta
        System.out.print("Escolha o tipo de conta (1 - Corrente, 2 - Poupança): ");
        int tipoConta = scanner.nextInt();

        Conta conta;
        if (tipoConta == 1) {
            conta = new ContaCorrente(cliente);
        } else if (tipoConta == 2) {
            conta = new ContaPoupanca(cliente);
        } else {
            System.out.println("Tipo de conta inválido. Criando Conta Corrente por padrão.");
            conta = new ContaCorrente(cliente);
        }

        // Operações na conta
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n------- Banco digital --------\n");
            System.out.println("Escolha uma opção:");
            System.out.println("\n1 - Depositar");
            System.out.println("2 - Sacar");
            System.out.println("3 - Transferir");
            System.out.println("4 - Imprimir Extrato");
            System.out.println("5 - Sair\n");

            int operacao = scanner.nextInt();

            switch (operacao) {
                case 1:
                    System.out.print("\nDigite o valor do depósito:");
                    double valorDeposito = scanner.nextDouble();
                    conta.depositar(valorDeposito);
                    break;

                case 2:
                    System.out.print("\nDigite o valor do saque:");
                    double valorSaque = scanner.nextDouble();
                    conta.sacar(valorSaque);
                    break;

                case 3:
                    System.out.print("\nDigite o valor da transferência:");
                    double valorTransferencia = scanner.nextDouble();
                    System.out.println("Transferir para (1 - Nova Conta Corrente, 2 - Nova Conta Poupança):");
                    int tipoContaTransferencia = scanner.nextInt();
                    Conta contaDestino;
                    if (tipoContaTransferencia == 1) {
                        contaDestino = new ContaCorrente(cliente);
                    } else {
                        contaDestino = new ContaPoupanca(cliente);
                    }
                    conta.transferir(valorTransferencia, contaDestino);
                    contaDestino.imprimirExtrato();
                    break;

                case 4:
                    conta.imprimirExtrato();
                    break;

                case 5:
                    continuar = false;
                    System.out.println("Encerrando atividade!");
                    break;

                default:
                    System.out.println("Opção inexistente. Tente novamente!");
            }
        }

        scanner.close();
    }
}
