package system.myBank.app.entity;

import java.io.Serializable;

public class TransactionInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5324181224163844731L;
	private String accountID;
	private String deposit;
	private String withdraw;

	public TransactionInfo(String accountID, String withdraw, String deposit) {
		super();

		this.accountID = accountID;
		this.deposit = deposit;
		this.withdraw = withdraw;

	}

	public TransactionInfo() {
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public String getDeposit() {
		return deposit;
	}

	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}

	public String getWithdraw() {
		return withdraw;
	}

	public void setWithdraw(String withdraw) {
		this.withdraw = withdraw;
	}

}
