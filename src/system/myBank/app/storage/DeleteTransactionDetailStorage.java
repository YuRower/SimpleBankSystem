package system.myBank.app.storage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import system.myBank.app.entity.Transaction;

public class DeleteTransactionDetailStorage {

	ArrayList<Transaction> lst;

	public DeleteTransactionDetailStorage(int index) {
		{
			try {
				FileInputStream fin = new FileInputStream("Banking.dat");
				ObjectInputStream oin = new ObjectInputStream(fin);
				lst = (ArrayList<Transaction>) oin.readObject();

				lst.remove(index);

				FileOutputStream fout = new FileOutputStream("Banking.dat");
				ObjectOutputStream oout = new ObjectOutputStream(fout);
				oout.writeObject(lst);
			} catch (Exception e) {
				lst = new ArrayList<Transaction>();
			}

		}

	}

}
