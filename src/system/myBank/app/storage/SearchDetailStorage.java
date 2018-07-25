package system.myBank.app.storage;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.apache.log4j.Logger;

import system.myBank.app.entity.Registration;

public class SearchDetailStorage extends JFrame {
	int index;
	static Logger logger = Logger.getLogger(SearchDetailStorage.class);

	public SearchDetailStorage(int index) {
		super("Search Window");
		logger.info("SearchDetailStorage");
		String heading[] = { "Name", "DOB", "Gender", "Phoneno", "Address", "emailid", "accounttype", "depositamount",
				"accountno" };
		String data[][] = new String[20][9];
		ArrayList<Registration> list2;
		try {
			FileInputStream fin = new FileInputStream("Register.dat");
			ObjectInputStream oin = new ObjectInputStream(fin);
			list2 = (ArrayList<Registration>) oin.readObject();

			Registration re = list2.get(index);

			data[0][0] = re.getName();
			data[0][1] = re.getDOB();
			data[0][2] = re.getGender();
			data[0][3] = re.getPhoneNum();
			data[0][4] = re.getAddress();
			data[0][5] = re.getEmailID();

			data[0][6] = re.getAccountType();
			data[0][7] = re.getDepositAmount();

			data[0][8] = re.getAccountID();

			Container con = getContentPane();
			con.setLayout(new BorderLayout());

			JTable datatable = new JTable(data, heading);
			JScrollPane jsp = new JScrollPane(datatable);

			con.add(new JLabel("Information Details"), BorderLayout.NORTH);
			con.add(jsp, BorderLayout.CENTER);

			setSize(650, 300);

			setLocation(200, 200);
			setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
