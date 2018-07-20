package system.myBank.app.storage;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import system.myBank.app.entity.Registration;
import system.myBank.app.entity.Transaction;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.*;
import java.util.*;

public class CustomerDetailStorage extends JFrame {

	public String data[][] = new String[30][9];
	public static String tmp;

	public CustomerDetailStorage() {
		super("Account Details");
		String heading[] = { "Name", "DOB", "Gender", "Phoneno", "Address", "EmailId", "AccountType", "DepositAmount",
				"AccountN" };

		ArrayList<Registration> list = new ArrayList<Registration>();

		try {
			FileInputStream fin = new FileInputStream("Register.dat");
			ObjectInputStream oin = new ObjectInputStream(fin);
			list = (ArrayList<Registration>) oin.readObject();

			int cnt = 0, c = 0;
			for (Registration re : list) {
				data[cnt][0] = re.getName();
				data[cnt][1] = re.getDOB();
				data[cnt][2] = re.getGender();
				data[cnt][3] = re.getPhoneNum();
				data[cnt][4] = re.getAddress();
				data[cnt][5] = re.getEmailID();
				data[cnt][6] = re.getAccountType();
				data[cnt][8] = re.getAccountID();

				cnt++;
				c = 0;
			}

			ArrayList<Transaction> folder = new ArrayList<Transaction>();
			try {
				FileInputStream fin1 = new FileInputStream("Banking.dat");
				ObjectInputStream oin1 = new ObjectInputStream(fin1);
				folder = (ArrayList<Transaction>) oin1.readObject();

				int r1 = 0, c1 = 0;
				for (Transaction tn : folder) {

					data[r1][7] = tn.getDepositAmount();

					r1++;
					c = 0;
				}

			} catch (Exception e0) {
				e0.printStackTrace();
			}

			Container con = getContentPane();
			con.setLayout(new BorderLayout());

			JTable datatable = new JTable(data, heading);
			JScrollPane jsp = new JScrollPane(datatable);

			con.add(new JLabel("All Customer Details"), BorderLayout.NORTH);
			con.add(jsp, BorderLayout.CENTER);

			setSize(250, 300);

			setLocation(200, 200);
			setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String[][] getData() {
		return data;
	}

	public static void setTmp(String tmp) {
		CustomerDetailStorage.tmp = tmp;
	}

	public static void main(String args[]) {
		new CustomerDetailStorage();
	}
}
