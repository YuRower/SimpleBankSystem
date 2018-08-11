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
import system.myBank.app.storage.AmountDetailStorage;
import system.myBank.app.storage.TransactionInfoOperation;
import system.myBank.app.storage.TransactionOperation;
import system.myBank.app.utill.Regexp;

public class TransferInfo extends JFrame implements ActionListener {
	ArrayList<Transaction> listTransaction;
	ArrayList<TransactionInfo> listTransactionInfo;

	private JLabel lTransfer, lSenderID, lAmount, l4, lDate, lSkip, lReceiver;
	private JTextField senderAccount, receiverAccount, fieldAmount, fieldBalance;
	private JButton bOK;
	private JComboBox cday, cmonth, cyear;
	private int findID, findID1;
	private Pattern pattern;
	private Matcher matcher;
	private final int MAX_DAY = 31;
	private final int MAX_MONTH = 12;
	private final int START_YEAR = 1898;


	public TransferInfo(String title) {
		super(title);

		listTransaction = new ArrayList<Transaction>();
		listTransactionInfo = new ArrayList<TransactionInfo>();

		Container c = getContentPane();
		c.setLayout(new GridLayout(6, 2));

		String dValue[] = new String[MAX_DAY];
		for (int i = 0; i <= MAX_DAY - 1; i++) {
			dValue[i] = String.valueOf(i + 1);
		}
		cday = new JComboBox(dValue);

		String mValue[] = new String[MAX_MONTH];
		for (int i = 0; i <= MAX_MONTH - 1; i++) {
			mValue[i] = String.valueOf(i + 1);
		}
		cmonth = new JComboBox(mValue);

		int year = Year.now().getValue();
		String yValue[] = new String[year - START_YEAR + 1];
		int cnt = 0;
		for (int i = START_YEAR; i <= year; i++) {
			yValue[cnt] = String.valueOf(i);
			cnt++;
		}
		cyear = new JComboBox(yValue);

		JPanel cpanel = new JPanel();
		cpanel.add(cday);
		cpanel.add(cmonth);
		cpanel.add(cyear);

		bOK = new JButton("OK");
		bOK.addActionListener(this);

		lTransfer = new JLabel(" Transfer");
		lTransfer.setForeground(Color.BLUE);
		lSenderID = new JLabel("Source Account No:");
		lSenderID.setForeground(Color.BLACK);
		senderAccount = new JTextField(20);
		lReceiver = new JLabel("Target Account No:");
		lReceiver.setForeground(Color.BLACK);
		receiverAccount = new JTextField(20);
		lAmount = new JLabel("Amount:");
		lAmount.setForeground(Color.BLACK);
		fieldAmount = new JTextField(20);
		lDate = new JLabel("Date:");
		lDate.setForeground(Color.BLACK);
		lSkip = new JLabel("");

		c.add(lTransfer);
		c.add(new JLabel(""));
		c.add(lSenderID);
		c.add(senderAccount);
		c.add(lReceiver);
		c.add(receiverAccount);
		c.add(lAmount);
		c.add(fieldAmount);

		c.add(lDate);
		c.add(cpanel);
		c.add(lSkip);
		c.add(bOK);

		setSize(450, 325);
		setLocation(200, 200);
		setResizable(false);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {

		String amount = fieldAmount.getText();
		String accountSend = senderAccount.getText();
		String accountReceive = receiverAccount.getText();
		String credit = "";
		String debit = "";

		if (ae.getSource() == bOK) {

			String sender = senderAccount.getText();
			String receiver = receiverAccount.getText();
			String fAmount = fieldAmount.getText();

			pattern = Pattern.compile(Regexp.NUMBER_PATTERN);
			boolean result1 = validate(sender);
			boolean result2 = validate(receiver);
			boolean result3 = validate(fAmount);

			if (!result1) {
				senderAccount.setText("");
				JOptionPane.showMessageDialog(this, "Enter Valid Source Account no..");
			} else if (!result2) {
				receiverAccount.setText("");
				JOptionPane.showMessageDialog(this, "Enter Valid Target Account no..");
			} else if (!result3) {
				fieldAmount.setText("");
				JOptionPane.showMessageDialog(this, "Enter valid Amount...");

			}

			if ((result1) && (result2) && (result3))
				JOptionPane.showMessageDialog(this, "your amount will be updated..");

			Transaction ts1 = new Transaction(amount, accountSend);
			TransactionOperation transOp1 = new TransactionOperation();
			findID = transOp1.searchInfoID(ts1);

			if (findID == -1) {
				JOptionPane.showMessageDialog(this, "SENDER ACCOUNT NO.FOUND");
			} else {
				Transaction ts2 = new Transaction(amount, accountReceive);
				TransactionOperation transOp2 = new TransactionOperation();
				findID1 = transOp2.searchInfoID(ts2);

				if (findID1 == -1) {
					JOptionPane.showMessageDialog(this, "RECEIVER ACCOUNT NO.FOUND");
				} else {
					transOp2.withdrawDetailStorage(ts1, findID);
					TransactionInfo tn = new TransactionInfo(accountSend, amount, credit);
					TransactionInfoOperation dh = new TransactionInfoOperation();
					dh.addInfo(tn);
					transOp2.depositDetailStorage(ts2, findID1);
					TransactionInfo tg = new TransactionInfo(accountReceive, debit, amount);
					TransactionInfoOperation da = new TransactionInfoOperation();
					dh.addInfo(tg);
					new AmountDetailStorage();
				}
			}
		}

	}

	public boolean validate (final String str) {
		matcher = pattern.matcher(str);
		return matcher.matches();		
	}

	public static void main(String args[]) {
		new TransferInfo("Transfer");

	}

}
