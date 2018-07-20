package system.myBank.app.entity;

import java.io.Serializable;

public class Registration implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2809406255005037130L;
	private String name;
	private String DOB;
	private String gender;
	private String phoneNum;
	private String address;
	private String emailID;

	private String accountType;
	public String depositAmount;

	private String accountID;

	public Registration(String name, String phoneNum, String address, String emailID, String depositAmount,
			String accountID, String gender, String accountType, String DOB) {
		super();
		this.name = name;
		this.DOB = DOB;
		this.gender = gender;
		this.phoneNum = phoneNum;
		this.address = address;
		this.emailID = emailID;
		this.accountType = accountType;
		this.depositAmount = depositAmount;
		this.accountID = accountID;

	}

	public Registration() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
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