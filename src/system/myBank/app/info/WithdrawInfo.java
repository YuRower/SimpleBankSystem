package system.myBank.app.info;

import java.awt.Color;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Year;

import javax.swing.*;

import system.myBank.app.entity.Transaction;
import system.myBank.app.entity.TransactionInfo;
import system.myBank.app.security.Regexp;
import system.myBank.app.storage.AmountDetailStorage;
import system.myBank.app.storage.TransactionInfoOperation;
import system.myBank.app.storage.TransactionOperation;

public class WithdrawInfo extends JFrame implements ActionListener {

	private JLabel withDraw, accountNum, amount, date, skip;
	private JTextField tAccountNum, tAmount, tBalance;
	private JButton bOK;
	private JComboBox boxDay, boxMonth, boxYear;
	private Pattern pattern;
	private Matcher matcher;
	private final int MAX_DAY = 31;
	private final int MAX_MONTH = 12;
	private final int START_YEAR = 1898;
	private int found, enterValidAcc = 0, enterValidAmo = 0;

	ArrayList<Transaction> listTransaction;
	ArrayList<TransactionInfo> listTransactionInfo;

	public WithdrawInfo(String title) {
		super(title);

		listTransaction = new ArrayList<Transaction>();
		listTransactionInfo = new ArrayList<TransactionInfo>();

		Container c = getContentPane();
		c.setLayout(new GridLayout(5, 2));

		String dValue[] = new String[MAX_DAY];
		for (int i = 0; i <= MAX_DAY - 1; i++) {
			dValue[i] = String.valueOf(i + 1);
		}
		boxDay = new JComboBox(dValue);

		String mValue[] = new String[MAX_MONTH];
		for (int i = 0; i <= MAX_MONTH - 1; i++) {
			mValue[i] = String.valueOf(i + 1);
		}
		boxMonth = new JComboBox(mValue);

		int year = Year.now().getValue();
		String yValue[] = new String[year - START_YEAR + 1];
		int cnt = 0;
		for (int i = START_YEAR; i <= year; i++) {
			yValue[cnt] = String.valueOf(i);
			cnt++;
		}
		boxYear = new JComboBox(yValue);

		JPanel cpanel = new JPanel();
		cpanel.add(boxDay);
		cpanel.add(boxMonth);
		cpanel.add(boxYear);

		bOK = new JButton("OK");
		bOK.addActionListener(this);

		withDraw = new JLabel("  Cash Withdraw");

		withDraw.setForeground(Color.BLUE);

		accountNum = new JLabel("     Account No:");

		accountNum.setForeground(Color.BLACK);
		tAccountNum = new JTextField(20);

		amount = new JLabel("     Amount:");

		amount.setForeground(Color.BLACK);
		tAmount = new JTextField(20);

		date = new JLabel("     Date:");

		date.setForeground(Color.BLACK);

		skip = new JLabel("");

		c.add(withDraw);
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
		String withdraw = "";
		String account = tAccountNum.getText();
		String amount = tAmount.getText();
		if (ae.getSource() == bOK) {

			pattern = Pattern.compile(Regexp.ACCOUNT_PATTERN);
			boolean result = validate(account);
			boolean result1 = validate(amount);

			if (!result) {
				tAccountNum.setText("");
				JOptionPane.showMessageDialog(this, "Enter Valid Account no..");
			} else if (!result1) {
				tAmount.setText("");
				JOptionPane.showMessageDialog(this, "Enter valid Amount...");
			} else if ((result) && (result1)) {
				JOptionPane.showMessageDialog(this, "your amount will be debited..");
			}

			Transaction ts = new Transaction(amount, account);
			TransactionOperation transOp = new TransactionOperation();
			found = transOp.searchInfoID(ts);

			if (found == -1) {
				JOptionPane.showMessageDialog(this, "NO DATA FOUND");
			} else if ((result) && (result1) && !(found == -1)) {
				transOp.withdrawDetailStorage(ts, found);
				new AmountDetailStorage();

				TransactionInfo tr = new TransactionInfo(account, amount, "");

				TransactionInfoOperation dt = new TransactionInfoOperation();
				dt.addInfo(tr);
			}
		}
	}

	public boolean validate (final String str) {
		matcher = pattern.matcher(str);
		return matcher.matches();		
	}

	public static void main(String args[]) {
		new WithdrawInfo("Withdraw");

	}

}
