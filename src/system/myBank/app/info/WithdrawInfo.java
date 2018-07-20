package system.myBank.app.info;

import java.awt.Color;
import java.util.*;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import system.myBank.app.entity.Transaction;
import system.myBank.app.entity.TransactionInfo;
import system.myBank.app.storage.AmountDetailStorage;
import system.myBank.app.storage.BankTransactionDetailStorage;
import system.myBank.app.storage.SearchTransactionDetailStorage;
import system.myBank.app.storage.WithdrawDetailStorage;

public class WithdrawInfo extends JFrame implements ActionListener {

	private JLabel withDraw, accN, amount, date, skip;
	private JTextField tAccountNo, tAmount, tBalance;
	private JButton bOK;
	private JComboBox boxDay, boxMonth, boxYear;
	private int notFound, enterValidAcc = 0, enterValidAmo = 0;

	ArrayList<Transaction> folder;
	ArrayList<TransactionInfo> match;

	public WithdrawInfo(String title) {
		super(title);

		folder = new ArrayList<Transaction>();
		match = new ArrayList<TransactionInfo>();

		Container c = getContentPane();
		c.setLayout(new GridLayout(5, 2));

		String dataDay[] = new String[31];
		for (int i = 0; i <= 30; i++) {
			dataDay[i] = String.valueOf(i + 1);
		}
		boxDay = new JComboBox(dataDay);

		String dataMonth[] = new String[12];
		for (int i = 0; i <= 11; i++) {
			dataMonth[i] = String.valueOf(i + 1);
		}
		boxMonth = new JComboBox(dataMonth);

		String dataYear[] = new String[112];
		int cnt = 0;
		for (int i = 1900; i <= 2011; i++) {
			dataYear[cnt] = String.valueOf(i);
			cnt++;
		}
		boxYear = new JComboBox(dataYear);

		JPanel cpanel = new JPanel();
		cpanel.add(boxDay);
		cpanel.add(boxMonth);
		cpanel.add(boxYear);

		bOK = new JButton("OK");
		bOK.addActionListener(this);

		withDraw = new JLabel("  Cash Withdraw");

		withDraw.setForeground(Color.BLUE);

		accN = new JLabel("     Account No:");

		accN.setForeground(Color.BLACK);
		tAccountNo = new JTextField(20);

		amount = new JLabel("     Amount:");

		amount.setForeground(Color.BLACK);
		tAmount = new JTextField(20);

		date = new JLabel("     Date:");

		date.setForeground(Color.BLACK);

		skip = new JLabel("");

		c.add(withDraw);
		c.add(new JLabel(""));
		c.add(accN);
		c.add(tAccountNo);
		c.add(amount);
		c.add(tAmount);

		c.add(date);
		c.add(cpanel);
		c.add(skip);
		c.add(bOK);

		setSize(450, 325);
		setLocation(200, 200);
		setResizable(false);
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {

		String amount = tAmount.getText();
		String accountno = tAccountNo.getText();
		String withdraw = "";

		if (ae.getSource() == bOK) {

			String s1 = tAccountNo.getText();
			String reg = "^\\d+$";
			Scanner sc = new Scanner(s1);
			String result = sc.findInLine(reg);

			if (result == null) {
				tAccountNo.setText("");
				JOptionPane.showMessageDialog(this, "Enter Valid Account no..");
				enterValidAcc = 1;
				if ((enterValidAcc == 0) && (enterValidAmo == 0))
					JOptionPane.showMessageDialog(this, "your amount will be debited..");
			}

			String s2 = tAmount.getText();
			String reg1 = "^\\d+$";
			Scanner sc1 = new Scanner(s2);
			String result1 = sc1.findInLine(reg1);
			if (result1 == null) {
				tAmount.setText("");
				JOptionPane.showMessageDialog(this, "Enter valid Amount...");
				enterValidAmo = 1;
				if ((enterValidAcc == 0) && (enterValidAmo == 0))
					JOptionPane.showMessageDialog(this, "your amount will be debited..");
			}

			if ((result != null) && (result1 != null))
				JOptionPane.showMessageDialog(this, "your amount will be debited..");

			Transaction ts = new Transaction(amount, accountno);
			SearchTransactionDetailStorage scr = new SearchTransactionDetailStorage();
			notFound = scr.searchaccountno(ts);

			if (notFound == -1) {
				JOptionPane.showMessageDialog(this, "NO DATA FOUND");
			} else if ((result != null) && (result1 != null) && !(notFound == -1)) {
				new WithdrawDetailStorage(ts, notFound);
				new AmountDetailStorage();

				TransactionInfo tr = new TransactionInfo(accountno, amount, "");
				BankTransactionDetailStorage dt = new BankTransactionDetailStorage(tr);
			}

		}

	}

	public static void main(String args[]) {
		new WithdrawInfo("Withdraw");

	}

}
