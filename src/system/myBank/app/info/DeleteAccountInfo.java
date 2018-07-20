package system.myBank.app.info;

import java.awt.Color;

import java.util.*;
import java.awt.Container;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import system.myBank.app.entity.Registration;
import system.myBank.app.entity.Transaction;
import system.myBank.app.storage.AmountDetailStorage;
import system.myBank.app.storage.CustomerDetailStorage;
import system.myBank.app.storage.DeleteRegistrationDetailStorage;
import system.myBank.app.storage.DeleteTransactionDetailStorage;
import system.myBank.app.storage.SearchRegistrDetailStorage;
import system.myBank.app.storage.SearchTransactionDetailStorage;

public class DeleteAccountInfo extends JFrame implements ActionListener {

	ArrayList<Registration> list;
	ArrayList<Transaction> folder;

	private JLabel lAccountNo;
	private JButton bok;
	private JTextField tAcntno;
	private int notFound, a1, enterValid = 0;

	public DeleteAccountInfo(String title) {
		super(title);

		list = new ArrayList<Registration>();
		folder = new ArrayList<Transaction>();

		Container c = getContentPane();
		c.setLayout(new GridLayout(5, 2));

		lAccountNo = new JLabel("  Enter Account No.:");

		lAccountNo.setForeground(Color.BLACK);
		tAcntno = new JTextField(20);

		bok = new JButton("OK");
		bok.addActionListener(this);

		c.add(new JLabel(""));
		c.add(new JLabel(""));
		c.add(lAccountNo);
		c.add(tAcntno);
		c.add(new JLabel(""));
		c.add(new JLabel(""));
		c.add(new JLabel(""));
		c.add(bok);
		c.add(new JLabel(""));
		c.add(new JLabel(""));

		setSize(400, 180);
		setLocation(200, 200);
		setResizable(false);

		setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		String accountno = tAcntno.getText();
		String amount = "";

		String name = "";
		String phoneno = "";
		String address = "";
		String emailid = "";
		String depositamount = "";
		String gender = "";
		String accounttype = "";
		String dob = "";

		if (ae.getSource() == bok) {

			String s1 = tAcntno.getText();
			String reg = "^[0-9]";
			Scanner sc = new Scanner(s1);
			String result = sc.findInLine(reg);

			if (result == null) {
				tAcntno.setText("");
				JOptionPane.showMessageDialog(this, "Enter Valid Account No..");
				enterValid = 1;
			}
			if (enterValid == 0) {
				JOptionPane.showMessageDialog(this, "Your account will be deleted..");
			}

			Registration r = new Registration(name, phoneno, address, emailid, depositamount, accountno, gender,
					accounttype, dob);

			SearchRegistrDetailStorage scr = new SearchRegistrDetailStorage();
			notFound = scr.searchaccountno(r);

			Transaction tst = new Transaction(amount, accountno);
			SearchTransactionDetailStorage sa = new SearchTransactionDetailStorage();
			a1 = sa.searchaccountno(tst);

			if (a1 == -1) {
				JOptionPane.showMessageDialog(this, "NO SUCH ACCOUNT FOUND");
			} else {
				new DeleteTransactionDetailStorage(a1);
				new AmountDetailStorage();
			}

			if (notFound == -1) {
				JOptionPane.showMessageDialog(this, "NO SUCH ACCOUNT FOUND");
			} else {
				new DeleteRegistrationDetailStorage(notFound);
				new CustomerDetailStorage();
			}

		}

	}

	public static void main(String[] args) {
		new DeleteAccountInfo(" Delete Account ");

	}

}
