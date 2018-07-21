package system.myBank.app.storage;

import system.myBank.app.entity.Transaction;

import java.io.*;
import java.util.*;

public class SearchTransactionDetailStorage {
	private ArrayList<Transaction> folder1;
	private int f = -1;

	public int searchAccountID(Transaction tsn) {
		try {
			FileInputStream fin = new FileInputStream("Banking.dat");
			ObjectInputStream oin = new ObjectInputStream(fin);
			folder1 = (ArrayList<Transaction>) oin.readObject();

			for (int p = 0; p < folder1.size(); p++) {
				if ((tsn.getAccountID()).equals(folder1.get(p).getAccountID().trim())) {
					f = p;
					break;
				}
			}

			return (f);

		} catch (Exception e) {
			return (-1);

		}

	}
}