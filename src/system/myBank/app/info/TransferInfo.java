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
import system.myBank.app.storage.AddTransactionDetailStorage;
import system.myBank.app.storage.DepositDetailStorage;
import system.myBank.app.storage.SearchTransactionDetailStorage;
import system.myBank.app.storage.WithdrawDetailStorage;

public class TransferInfo extends JFrame implements ActionListener {
	ArrayList<Transaction> folder;
	ArrayList<TransactionInfo> match;

	private JLabel l1, l2, l3, l4, l5, l6, l7;
	private JTextField tsourceaccount, ttargetaccount, tamount, tnetbalance;
	private JButton bok;
	private JComboBox cday, cmonth, cyear;
	private int a, b, m = 0, n = 0, l = 0;

	public TransferInfo(String title) {
		super(title);

		folder = new ArrayList<Transaction>();
		match = new ArrayList<TransactionInfo>();

		Container c = getContentPane();
		c.setLayout(new GridLayout(6, 2));

		String dvalue[] = new String[31];
		for (int i = 0; i <= 30; i++) {
			dvalue[i] = String.valueOf(i + 1);
		}
		cday = new JComboBox(dvalue);

		String mvalue[] = new String[12];
		for (int i = 0; i <= 11; i++) {
			mvalue[i] = String.valueOf(i + 1);
		}
		cmonth = new JComboBox(mvalue);

		String yvalue[] = new String[112];
		int cnt = 0;
		for (int i = 1900; i <= 2011; i++) {
			yvalue[cnt] = String.valueOf(i);
			cnt++;
		}
		cyear = new JComboBox(yvalue);

		JPanel cpanel = new JPanel();
		cpanel.add(cday);
		cpanel.add(cmonth);
		cpanel.add(cyear);

		bok = new JButton("OK");
		bok.addActionListener(this);

		l1 = new JLabel("Fund Transfer");

		l1.setForeground(Color.BLUE);

		l2 = new JLabel("Source Account No:");

		l2.setForeground(Color.BLACK);
		tsourceaccount = new JTextField(20);

		l7 = new JLabel("Target Account No:");

		l7.setForeground(Color.BLACK);
		ttargetaccount = new JTextField(20);

		l3 = new JLabel("Amount:");

		l3.setForeground(Color.BLACK);
		tamount = new JTextField(20);

		/*
		 * l4=new JLabel("Net Balance:"); l4.setFont(f2); l4.setForeground(Color.BLACK);
		 * tnetbalance=new JTextField(20);
		 */

		l5 = new JLabel("Date:");

		l5.setForeground(Color.BLACK);

		l6 = new JLabel("");

		c.add(l1);
		c.add(new JLabel(""));
		c.add(l2);
		c.add(tsourceaccount);
		c.add(l7);
		c.add(ttargetaccount);
		c.add(l3);
		c.add(tamount);

		c.add(l5);
		c.add(cpanel);
		c.add(l6);
		c.add(bok);

		setSize(450, 325);
		setLocation(200, 200);
		setResizable(false);
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {

		String amount = tamount.getText();
		String accountno1 = tsourceaccount.getText();
		String accountno2 = ttargetaccount.getText();
		String credit = "";
		String debit = "";

		if (ae.getSource() == bok) {

			String s1 = tsourceaccount.getText();
			String reg1 = "^[0-9]";
			Scanner sc1 = new Scanner(s1);
			String result1 = sc1.findInLine(reg1);

			if (result1 == null) {
				tsourceaccount.setText("");
				JOptionPane.showMessageDialog(this, "Enter Valid Source Account no..");
				m = 1;
				if ((m == 0) && (n == 0) && (l == 0))
					JOptionPane.showMessageDialog(this, "your amount will be credited..");
			}

			String s2 = ttargetaccount.getText();
			String reg2 = "^[0-9]";
			Scanner sc2 = new Scanner(s2);
			String result2 = sc2.findInLine(reg2);

			if (result2 == null) {
				ttargetaccount.setText("");
				JOptionPane.showMessageDialog(this, "Enter Valid Target Account no..");
				l = 1;
				if ((m == 0) && (n == 0) && (l == 0))
					JOptionPane.showMessageDialog(this, "your amount will be credited..");
			}

			String s3 = tamount.getText();
			String reg3 = "^[0-9]";
			Scanner sc3 = new Scanner(s3);
			String result3 = sc3.findInLine(reg3);
			if (result3 == null) {
				tamount.setText("");
				JOptionPane.showMessageDialog(this, "Enter valid Amount...");
				n = 1;
				if ((m == 0) && (n == 0) && (l == 0))
					JOptionPane.showMessageDialog(this, "your amount will be debited..");
			}

			if ((result1 != null) && (result2 != null) && (result3 != null))
				JOptionPane.showMessageDialog(this, "your amount will be updated..");

			Transaction ts1 = new Transaction(amount, accountno1);
			SearchTransactionDetailStorage scr1 = new SearchTransactionDetailStorage();
			a = scr1.searchAccountID(ts1);

			if (a == -1) {
				JOptionPane.showMessageDialog(this, "NO SUCH ACCOUNT NO.FOUND");
			} else {

				Transaction ts2 = new Transaction(amount, accountno2);
				SearchTransactionDetailStorage scr2 = new SearchTransactionDetailStorage();
				b = scr2.searchAccountID(ts2);

				if (b == -1) {
					JOptionPane.showMessageDialog(this, "NO SUCH ACCOUNT NO.FOUND");
				} else {
					new WithdrawDetailStorage(ts1, a);
					TransactionInfo tn = new TransactionInfo(accountno1, amount, credit);
					AddTransactionDetailStorage dh = new AddTransactionDetailStorage(tn);

					new DepositDetailStorage(ts2, b);
					TransactionInfo tg = new TransactionInfo(accountno2, debit, amount);
					AddTransactionDetailStorage da = new AddTransactionDetailStorage(tg);
					// DataInfo();
					new AmountDetailStorage();
				}
			}
		}

	}

	public static void main(String args[]) {
		new TransferInfo("Transfer");

	}

}
