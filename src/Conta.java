public abstract class Conta implements IConta {

	private static final int AGENCIA_PADRAO = 1;
	private static int SEQUENCIAL = 1;

	protected int agencia;
	protected int numero;
	protected double saldo;
	protected Cliente cliente;

	public Conta(Cliente cliente) {
		this.agencia = Conta.AGENCIA_PADRAO;
		this.numero = SEQUENCIAL++;
		this.cliente = cliente;
	}

	@Override
	public void sacar(double valor) {
		if (valor > 0 && valor <= saldo) {
			saldo -= valor;
			System.out.println(String.format("Saque de R$%.2f realizado com sucesso.", valor));
		} else if (valor > saldo) {
			System.out.println("Saldo insuficiente para realizar o saque.");
		} else {
			System.out.println("Valor de saque inválido.");
		}
	}

	@Override
	public void depositar(double valor) {
		if (valor > 0) {
			saldo += valor;
			System.out.println(String.format("Depósito de R$%.2f realizado com sucesso.", valor));
		} else {
			System.out.println("Valor de depósito inválido.");
		}
	}

	@Override
	public void transferir(double valor, IConta contaDestino) {
		if (valor > 0 && saldo >= valor) {
			this.sacar(valor);
			contaDestino.depositar(valor);
			System.out.println(String.format("Transferência de R$%.2f realizada com sucesso.", valor));
		} else if (valor > saldo) {
			System.out.println("Saldo insuficiente para realizar a transferência.");
		} else {
			System.out.println("Valor de transferência inválido.");
		}
	}

	public int getAgencia() {
		return agencia;
	}

	public int getNumero() {
		return numero;
	}

	public double getSaldo() {
		return saldo;
	}

	protected void imprimirInfosComuns() {
		System.out.println(String.format("Titular: %s", this.cliente.getNome()));
		System.out.println(String.format("Agência: %d", this.agencia));
		System.out.println(String.format("Número: %d", this.numero));
		System.out.println(String.format("Saldo: %.2f", this.saldo));
	}
}
