package system.myBank.app.info;

import java.awt.Color;
import java.io.*;
import java.time.Year;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import system.myBank.app.entity.Transaction;
import system.myBank.app.entity.TransactionInfo;
import system.myBank.app.security.Regexp;
import system.myBank.app.storage.AmountDetailStorage;
import system.myBank.app.storage.TransactionInfoOperation;
import system.myBank.app.storage.TransactionOperation;

enum EnterValid {
	INVALID_ENTER, VALID_ENTER;
}

public class DepositInfo extends JFrame implements ActionListener {
	public EnterValid enterValid, validAccount, validAmount;

	private JLabel cashDeposit, accountNum, amount, date, skip;
	private JTextField tAccountNum, tAmount, tBalance;
	private JButton bOK;
	private JComboBox cbDay, cbMonth, cbYear;
	private int found;
	private Pattern pattern;
	private Matcher matcher;
	private final int MAX_DAY = 31;
	private final int MAX_MONTH = 12;
	private final int START_YEAR = 1898;


	ArrayList<Transaction> listTransaction;
	ArrayList<TransactionInfo> listTransInfo;

	public DepositInfo(String title) {
		super(title);
		listTransaction = new ArrayList<Transaction>();
		listTransInfo = new ArrayList<TransactionInfo>();

		Container c = getContentPane();
		c.setLayout(new GridLayout(5, 2));

		String dValue[] = new String[MAX_DAY];
		for (int i = 0; i <= MAX_DAY - 1; i++) {
			dValue[i] = String.valueOf(i + 1);
		}
		cbDay = new JComboBox(dValue);

		String mValue[] = new String[MAX_MONTH];
		for (int i = 0; i <= MAX_MONTH - 1; i++) {
			mValue[i] = String.valueOf(i + 1);
		}
		cbMonth = new JComboBox(mValue);

		int year = Year.now().getValue();
		String yValue[] = new String[year - START_YEAR + 1];
		int cnt = 0;
		for (int i = START_YEAR; i <= year; i++) {
			yValue[cnt] = String.valueOf(i);
			cnt++;
		}
		cbYear = new JComboBox(yValue);

		JPanel cpanel = new JPanel();
		cpanel.add(cbDay);
		cpanel.add(cbMonth);
		cpanel.add(cbYear);

		bOK = new JButton("OK");
		bOK.addActionListener(this);

		cashDeposit = new JLabel("  Cash Deposit");
		cashDeposit.setForeground(Color.BLUE);

		accountNum = new JLabel("     Account No:");

		accountNum.setForeground(Color.BLACK);
		tAccountNum = new JTextField(20);

		amount = new JLabel("     Amount:");

		amount.setForeground(Color.BLACK);
		tAmount = new JTextField(20);

		date = new JLabel("     Date:");

		date.setForeground(Color.BLACK);

		skip = new JLabel("");

		c.add(cashDeposit);
		c.add(new JLabel(""));
		c.add(accountNum);
		c.add(tAccountNum);
		c.add(amount);
		c.add(tAmount);

		c.add(date);
		c.add(cpanel);
		c.add(skip);
		c.add(bOK);

		setSize(450, 325);
		setLocation(200, 200);
		setResizable(false);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {

		String amount = tAmount.getText();
		String accountNum = tAccountNum.getText();
		String deposit = "";

		if (ae.getSource() == bOK) {

			pattern = Pattern.compile(Regexp.ACCOUNT_PATTERN);
			boolean result = validate(accountNum);
			boolean result1 = validate(amount);

			if (!result) {
				tAccountNum.setText("");
				JOptionPane.showMessageDialog(this, "Enter Valid Account no..");
				enterValid = EnterValid.INVALID_ENTER;

			} else if (!result1) {
				tAmount.setText("");
				JOptionPane.showMessageDialog(this, "Enter valid Amount...");
				validAmount = EnterValid.INVALID_ENTER;

			}

			Transaction ts = new Transaction(amount, accountNum);
			TransactionOperation transaction = new TransactionOperation();
			found = transaction.searchInfoID(ts);

			if (found == -1 & validAmount==EnterValid.INVALID_ENTER & enterValid==EnterValid.INVALID_ENTER) {
				JOptionPane.showMessageDialog(this, "NO DATA FOUND");
			}

			if ((result) && (result1) && !(found == -1)) {
				JOptionPane.showMessageDialog(this, "your amount will be credited..");

				TransactionInfo tr = new TransactionInfo(accountNum, "", amount);
				transaction.depositDetailStorage(ts, found);
				new AmountDetailStorage();

				TransactionInfoOperation dt = new TransactionInfoOperation();
				dt.addInfo(tr);

			}

		}
	}

	public boolean validate(final String str) {
		matcher = pattern.matcher(str);
		return matcher.matches();
	}

	public static void main(String args[]) {
		new DepositInfo("Deposit");

	}

}
