package system.myBank.app;

import java.awt.*;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class HistoryTrans extends JFrame {
	public HistoryTrans() {
		super("Account Details");
		String heading[] = { "N", "Withdarw", "Deposit" };
		String data[][] = new String[30][3];
		ArrayList<TransactionInfo> list = new ArrayList<TransactionInfo>();

		try {
			FileInputStream fin = new FileInputStream("BankTrans.dat");
			ObjectInputStream oin = new ObjectInputStream(fin);
			list = (ArrayList<TransactionInfo>) oin.readObject();

			int r = 0, c = 0;
			for (TransactionInfo re : list) {
				data[r][0] = re.getaccountno();

				data[r][1] = re.getWithdraw();
				data[r][2] = re.getDeposit();

				++r;
				c = 0;

			}

			Container con = getContentPane();
			con.setLayout(new BorderLayout());

			JTable datatable = new JTable(data, heading);
			JScrollPane jsp = new JScrollPane(datatable);

			con.add(new JLabel("DataInfo Details"), BorderLayout.NORTH);
			con.add(jsp, BorderLayout.CENTER);

			setSize(250, 300);

			setLocation(200, 200);
			setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String args[]) {
		new HistoryTrans();
	}
}
