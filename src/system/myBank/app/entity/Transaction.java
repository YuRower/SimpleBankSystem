package system.myBank.app.entity;

import java.io.Serializable;

public class Transaction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6049970169603164444L;
	public String depositAmount;
	private String accountID;

	public Transaction(String depositAmount, String accountID) {

		super();
		this.depositAmount = depositAmount;
		this.accountID = accountID;
	}

	public Transaction() {

	}

	public String getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(String depositAmount) {
		this.depositAmount = depositAmount;
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

}
