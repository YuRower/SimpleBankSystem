package system.myBank.app.storage;

import java.util.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import system.myBank.app.entity.Transaction;

public class WithdrawDetailStorage {
	ArrayList<Transaction> lst;

	private int crdt;
	private final int OVERDRAFT = -100;

	public WithdrawDetailStorage(Transaction cr, int index) {
		try {
			FileInputStream fin = new FileInputStream("Banking.dat");
			ObjectInputStream oin = new ObjectInputStream(fin);
			lst = (ArrayList<Transaction>) oin.readObject();

			int fix1 = convert(index);
			int calc = fix1 - (Integer.parseInt(cr.getDepositAmount()));
			if (calc < OVERDRAFT) {
				JOptionPane.showMessageDialog(null, new String("Operation could not be completed"));
				return;
			}
			String s = String.valueOf(calc);

			lst.get(index).setDepositAmount(s);

		} catch (Exception e) {
			lst = new ArrayList<Transaction>();
		}

		try {
			FileOutputStream fout = new FileOutputStream("Banking.dat");
			ObjectOutputStream oout = new ObjectOutputStream(fout);
			oout.writeObject(lst);
		} catch (Exception e) {
		}

	}

	public int convert(int index) {
		int conv = Integer.parseInt(lst.get(index).getDepositAmount());
		return (conv);

	}

}