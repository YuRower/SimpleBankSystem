package system.myBank.app.storage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import system.myBank.app.entity.Transaction;
import system.myBank.app.entity.TransactionInfo;

public class TransactionInfoOperation extends AbstractBankOperation<TransactionInfo> {
	ArrayList<TransactionInfo> match;
	static Logger logger = Logger.getLogger(TransactionInfoOperation.class);

	@Override
	public void addInfo(TransactionInfo obj) {
		try {
			logger.info("addInfo");
			FileInputStream fin2 = new FileInputStream("BankTrans.dat");
			ObjectInputStream oin2 = new ObjectInputStream(fin2);
			match = (ArrayList<TransactionInfo>) oin2.readObject();
		} catch (Exception e) {
			match = new ArrayList<TransactionInfo>();
		}

		match.add(obj);

		try {
			FileOutputStream fout2 = new FileOutputStream("BankTrans.dat");
			ObjectOutputStream oout2 = new ObjectOutputStream(fout2);
			oout2.writeObject(match);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeInfo(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int searchInfoID(TransactionInfo obj) {
		throw new UnsupportedOperationException();
	}
}
