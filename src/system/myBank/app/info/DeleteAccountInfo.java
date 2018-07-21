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
import system.myBank.app.storage.RemoveRegistrationDetailStorage;
import system.myBank.app.storage.RemoveTransactionDetailStorage;
import system.myBank.app.storage.SearchRegistrDetailStorage;
import system.myBank.app.storage.SearchTransactionDetailStorage;

enum StatusEnter {
	INVALID_ENTER, VALID_ENTER;
}

public class DeleteAccountInfo extends JFrame implements ActionListener {

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

			SearchRegistrDetailStorage scr = new SearchRegistrDetailStorage();
			notFoundRegister = scr.searchAcountID(r);

			Transaction tst = new Transaction(amount, accountNum);
			SearchTransactionDetailStorage sa = new SearchTransactionDetailStorage();
			notFoundTransaction = sa.searchAccountID(tst);

			if (notFoundTransaction == -1) {
				JOptionPane.showMessageDialog(this, "NO SUCH Transaction FOUND");
			} else {
				new RemoveTransactionDetailStorage(notFoundTransaction);
				new AmountDetailStorage();
			}

			if (notFoundRegister == -1) {
				JOptionPane.showMessageDialog(this, "NO SUCH ACCOUNT FOUND");
			} else {
				new RemoveRegistrationDetailStorage(notFoundRegister);
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
