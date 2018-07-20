package system.myBank.app.storage;

import java.io.FileInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import system.myBank.app.entity.Transaction;

public class TransactionAddDetailStorage {
	ArrayList<Transaction> folder4;

	public TransactionAddDetailStorage(Transaction rg1) {
		try {
			FileInputStream fin = new FileInputStream("Banking.dat");
			ObjectInputStream oin = new ObjectInputStream(fin);
			folder4 = (ArrayList<Transaction>) oin.readObject();
		} catch (Exception e) {
			folder4 = new ArrayList<Transaction>();
		}

		folder4.add(rg1);

		try {
			FileOutputStream fout = new FileOutputStream("Banking.dat");
			ObjectOutputStream oout = new ObjectOutputStream(fout);
			oout.writeObject(folder4);
		} catch (Exception e) {
		}

	}

	public TransactionAddDetailStorage() {
	}
}
