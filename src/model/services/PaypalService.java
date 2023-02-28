package model.services;

public class PaypalService implements OnlinePaymentService {

	@Override
	public double paymentFee(double amount) {
		return amount + (amount * 0.02);
	}

	@Override
	public double interest(double amount, int months) {
		double total = amount / months;
		return total + (total * 0.01) * months;
	}

}
