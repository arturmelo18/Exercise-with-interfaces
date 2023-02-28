package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

public class Program {

	public static void main(String[] args) {

		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.println("Entre os dados do contrato: ");
		System.out.println("Número: ");
		int number = sc.nextInt();
		sc.nextLine();
		System.out.println("Data (dd/MM/yyyy): ");
		LocalDate date = LocalDate.parse(sc.nextLine(), fmt);
		System.out.println("Valor do contrato: ");
		double totalValue = sc.nextDouble();
		System.out.println("Entre com o número de parcelas: ");
		int n = sc.nextInt();

		Contract contract = new Contract(number, date, totalValue);
		ContractService cs = new ContractService(new PaypalService());

		System.out.println();
		System.out.println("Parcelas: ");
		cs.processContract(contract, n);

		for (Installment installment : contract.getInstallments()) {
			System.out.println(installment.getDueDate() + " - " + installment.getAmount());
		}

		sc.close();
	}

}
