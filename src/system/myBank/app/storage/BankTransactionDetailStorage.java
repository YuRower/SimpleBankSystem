package system.myBank.app.storage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import system.myBank.app.entity.TransactionInfo;

public class BankTransactionDetailStorage {
	ArrayList<TransactionInfo> match;

	public BankTransactionDetailStorage(TransactionInfo rg1) {
		try {
			FileInputStream fin2 = new FileInputStream("BankTrans.dat");
			ObjectInputStream oin2 = new ObjectInputStream(fin2);
			match = (ArrayList<TransactionInfo>) oin2.readObject();
		} catch (Exception e) {
			match = new ArrayList<TransactionInfo>();
		}

		match.add(rg1);

		try {
			FileOutputStream fout2 = new FileOutputStream("BankTrans.dat");
			ObjectOutputStream oout2 = new ObjectOutputStream(fout2);
			oout2.writeObject(match);
		} catch (Exception e) {
		}

	}
}
