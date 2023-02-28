package model.services;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {

	private OnlinePaymentService onlinePaymentService;

	public ContractService() {
	}

	public ContractService(OnlinePaymentService onlinePaymentService) {
		super();
		this.onlinePaymentService = onlinePaymentService;
	}

	public OnlinePaymentService getOnlinePaymentService() {
		return onlinePaymentService;
	}

	public void setOnlinePaymentService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}

	public void processContract(Contract contract, int months) {
		for (int i = 1; i <= months; i++) {
			double total = (contract.getTotalValue() / months)
					+ onlinePaymentService.interest((contract.getTotalValue() / months), i);
			total += onlinePaymentService.paymentFee(total);
			contract.addInstallment(new Installment(contract.getDate().plusMonths(i), total));
		}
	}
}
