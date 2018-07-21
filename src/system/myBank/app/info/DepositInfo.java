package system.myBank.app.info;

import java.awt.Color;
import java.io.*;
import java.util.*;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import system.myBank.app.entity.Transaction;
import system.myBank.app.entity.TransactionInfo;
import system.myBank.app.storage.AmountDetailStorage;
import system.myBank.app.storage.AddTransactionDetailStorage;
import system.myBank.app.storage.DepositDetailStorage;
import system.myBank.app.storage.SearchTransactionDetailStorage;

public class DepositInfo extends JFrame implements ActionListener {

	private JLabel depos, accNo, amou, date, skip;
	private JTextField tAccountNo, tAmount, tBalance;
	private JButton bok;
	private JComboBox cbDay, cbMonth, cbYear;
	private int norFound, enterValidAcc = 0, enterValidAmou = 0;

	ArrayList<Transaction> folder;
	ArrayList<TransactionInfo> match;

	public DepositInfo(String title) {
		super(title);

		folder = new ArrayList<Transaction>();
		match = new ArrayList<TransactionInfo>();

		Container c = getContentPane();
		c.setLayout(new GridLayout(5, 2));

		String dvalue[] = new String[31];
		for (int i = 0; i <= 30; i++) {
			dvalue[i] = String.valueOf(i + 1);
		}
		cbDay = new JComboBox(dvalue);

		String mvalue[] = new String[12];
		for (int i = 0; i <= 11; i++) {
			mvalue[i] = String.valueOf(i + 1);
		}
		cbMonth = new JComboBox(mvalue);

		String yvalue[] = new String[112];
		int cnt = 0;
		for (int i = 1900; i <= 2011; i++) {
			yvalue[cnt] = String.valueOf(i);
			cnt++;
		}
		cbYear = new JComboBox(yvalue);

		JPanel cpanel = new JPanel();
		cpanel.add(cbDay);
		cpanel.add(cbMonth);
		cpanel.add(cbYear);

		bok = new JButton("OK");
		bok.addActionListener(this);

		depos = new JLabel("  Cash Deposit");
		depos.setForeground(Color.BLUE);

		accNo = new JLabel("     Account No:");

		accNo.setForeground(Color.BLACK);
		tAccountNo = new JTextField(20);

		amou = new JLabel("     Amount:");

		amou.setForeground(Color.BLACK);
		tAmount = new JTextField(20);

		date = new JLabel("     Date:");

		date.setForeground(Color.BLACK);

		skip = new JLabel("");

		c.add(depos);
		c.add(new JLabel(""));
		c.add(accNo);
		c.add(tAccountNo);
		c.add(amou);
		c.add(tAmount);

		c.add(date);
		c.add(cpanel);
		c.add(skip);
		c.add(bok);

		setSize(450, 325);
		setLocation(200, 200);
		setResizable(false);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {

		String amount = tAmount.getText();
		String accountno = tAccountNo.getText();
		String deposit = "";

		if (ae.getSource() == bok) {

			String s1 = tAccountNo.getText();
			String reg = "^\\d+$";
			Scanner sc = new Scanner(s1);
			String result = sc.findInLine(reg);

			if (result == null) {
				tAccountNo.setText("");
				JOptionPane.showMessageDialog(this, "Enter Valid Account no..");
				enterValidAcc = 1;
				if ((enterValidAcc == 0) && (enterValidAmou == 0))
					JOptionPane.showMessageDialog(this, "your amount will be credited..");
			}

			String s2 = tAmount.getText();
			String reg1 = "^\\d+$";
			Scanner sc1 = new Scanner(s2);
			String result1 = sc1.findInLine(reg1);
			if (result1 == null) {
				tAmount.setText("");
				JOptionPane.showMessageDialog(this, "Enter valid Amount...");
				enterValidAmou = 1;
				if ((enterValidAcc == 0) && (enterValidAmou == 0))
					JOptionPane.showMessageDialog(this, "your amount will be credited..");
			}

			if ((result != null) && (result1 != null))
				JOptionPane.showMessageDialog(this, "your amount will be credited..");

			Transaction ts = new Transaction(amount, accountno);
			SearchTransactionDetailStorage scr = new SearchTransactionDetailStorage();
			norFound = scr.searchAccountID(ts);

			if (norFound == -1) {
				JOptionPane.showMessageDialog(this, "NO DATA FOUND");
			}
			if ((result != null) && (result1 != null) && !(norFound == -1)) {
				TransactionInfo tr = new TransactionInfo(accountno, "", amount);
				new DepositDetailStorage(ts, norFound);
				new AmountDetailStorage();

				AddTransactionDetailStorage dt = new AddTransactionDetailStorage(tr);

			}

		}
	}

	public static void main(String args[]) {
		new DepositInfo("Deposit");

	}

}
