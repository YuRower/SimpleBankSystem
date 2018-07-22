package system.myBank.app.info;

import java.awt.Color;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.Container;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import system.myBank.app.entity.Registration;
import system.myBank.app.entity.Transaction;
import system.myBank.app.storage.AmountDetailStorage;
import system.myBank.app.storage.CustomerDetailStorage;
import system.myBank.app.storage.RegisterOperation;
import system.myBank.app.storage.TransactionOperation;

enum StatusEnter {
	INVALID_ENTER, VALID_ENTER;
}

public class DeleteAccountInfo extends JFrame implements ActionListener {
	TransactionOperation transaction = TransactionOperation();
	ArrayList<Registration> listRegistr;
	ArrayList<Transaction> listTransaction;
	public StatusEnter statusEnter;
	private JLabel lAccountNum;
	private JButton buttonOK;
	private JTextField fieldID;
	private int notFoundRegister, notFoundTransaction;
	private static final String NUMBER_PATTERN = "^[0-9]";
	private Pattern pattern;
	private Matcher matcher;
	public RegisterOperation register = new RegisterOperation() ;

	public DeleteAccountInfo(String title) {
		super(title);
		listRegistr = new ArrayList<Registration>();
		listTransaction = new ArrayList<Transaction>();
		Container c = getContentPane();
		c.setLayout(new GridLayout(5, 2));
		lAccountNum = new JLabel("  Enter Account No.:");
		lAccountNum.setForeground(Color.BLACK);
		fieldID = new JTextField(20);
		buttonOK = new JButton("OK");
		buttonOK.addActionListener(this);
		c.add(new JLabel(""));
		c.add(new JLabel(""));
		c.add(lAccountNum);
		c.add(fieldID);
		c.add(new JLabel(""));
		c.add(new JLabel(""));
		c.add(new JLabel(""));
		c.add(buttonOK);
		c.add(new JLabel(""));
		c.add(new JLabel(""));
		setSize(400, 180);
		setLocation(200, 200);
		setResizable(false);
		setVisible(true);
	}

	private TransactionOperation TransactionOperation() {
		// TODO Auto-generated method stub
		return null;
	}

	public void actionPerformed(ActionEvent ae) {
		String accountNum = fieldID.getText();
		String amount = "";
		String name = "";
		String phoneNum = "";
		String address = "";
		String emailID = "";
		String depositAmount = "";
		String gender = "";
		String accountType = "";
		String DOB = "";

		if (ae.getSource() == buttonOK) {

			String accountID = String.valueOf(fieldID.getText());
			pattern = Pattern.compile(NUMBER_PATTERN);
			boolean result = validate(accountID);

			if (!result) {
				fieldID.setText("");
				JOptionPane.showMessageDialog(this, "Enter Valid Account No..");
				statusEnter = StatusEnter.INVALID_ENTER;
			}
			if (statusEnter == StatusEnter.VALID_ENTER) {
				JOptionPane.showMessageDialog(this, "Your account will be deleted..");
			}

			Registration r = new Registration(name, phoneNum, address, emailID, depositAmount, accountNum, gender,
					accountType, DOB);

			RegisterOperation scr = new RegisterOperation();
			notFoundRegister = scr.searchInfoID(r);

			Transaction tst = new Transaction(amount, accountNum);
			TransactionOperation transaction = new TransactionOperation();
			notFoundTransaction = transaction.searchAccountID(tst);

			if (notFoundTransaction == -1) {
				JOptionPane.showMessageDialog(this, "NO SUCH Transaction FOUND");
			} else {
				transaction.RemoveTransactionDetailStorage(notFoundTransaction);
				new AmountDetailStorage();
			}

			if (notFoundRegister == -1) {
				JOptionPane.showMessageDialog(this, "NO SUCH ACCOUNT FOUND");
			} else {
				register.removeInfo(notFoundRegister);
				new CustomerDetailStorage();
			}

		}

	}

	public boolean validate(final String accountID) {
		matcher = pattern.matcher(accountID);
		return matcher.matches();

	}

	public static void main(String[] args) {
		new DeleteAccountInfo(" Delete Account ");

	}

}
