package system.myBank.app.storage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import system.myBank.app.entity.Registration;
import system.myBank.app.entity.Transaction;

public class TransactionOperation extends AbstractBankOperation<Transaction> {
	ArrayList<Transaction> listTransaction;
	private final int OVERDRAFT = -100;
	static Logger logger = Logger.getLogger(TransactionOperation.class);

	private final String TRANSACTION_FILE = "Banking.dat";

	public TransactionOperation() {

	}

	private ArrayList<Transaction> folder1;
	private int f = -1;

	public void withdrawDetailStorage(Transaction cr, int index) {
		try {
			logger.info("withdrawDetailStorage");
			FileInputStream fin = new FileInputStream(TRANSACTION_FILE);
			ObjectInputStream oin = new ObjectInputStream(fin);
			listTransaction = (ArrayList<Transaction>) oin.readObject();

			int fix1 = convert(index);
			int calc = fix1 - (Integer.parseInt(cr.getDepositAmount()));
			if (calc < OVERDRAFT) {
				JOptionPane.showMessageDialog(null, new String("Operation could not be completed"));
				return;
			}
			String s = String.valueOf(calc);

			listTransaction.get(index).setDepositAmount(s);

		} catch (Exception e) {
			listTransaction = new ArrayList<Transaction>();
		}

		try {
			FileOutputStream fout = new FileOutputStream(TRANSACTION_FILE);
			ObjectOutputStream oout = new ObjectOutputStream(fout);
			oout.writeObject(listTransaction);
		} catch (Exception e) {
		}

	}

	public int convert(int index) {
		int conv = Integer.parseInt(listTransaction.get(index).getDepositAmount());
		return (conv);

	}

	public void depositDetailStorage(Transaction cr, int index) {
		try {
			logger.info("depositDetailStorage");

			FileInputStream fin = new FileInputStream(TRANSACTION_FILE);
			ObjectInputStream oin = new ObjectInputStream(fin);
			listTransaction = (ArrayList<Transaction>) oin.readObject();

			int fix1 = convert(index);
			int calc = fix1 + (Integer.parseInt(cr.getDepositAmount()));
			String fix2 = listTransaction.get(index).getAccountID();

			String s = String.valueOf(calc);
			listTransaction.get(index).setDepositAmount(s);

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			FileOutputStream fout = new FileOutputStream(TRANSACTION_FILE);
			ObjectOutputStream oout = new ObjectOutputStream(fout);
			oout.writeObject(listTransaction);
		} catch (Exception e) {
		}

	}

	@Override
	public void addInfo(Transaction obj) {
		try {
			logger.debug("addInfo");

			FileInputStream fin = new FileInputStream(TRANSACTION_FILE);
			ObjectInputStream oin = new ObjectInputStream(fin);
			listTransaction = (ArrayList<Transaction>) oin.readObject();
		} catch (Exception e) {
			listTransaction = new ArrayList<Transaction>();
		}

		listTransaction.add(obj);

		try {
			FileOutputStream fout = new FileOutputStream(TRANSACTION_FILE);
			ObjectOutputStream oout = new ObjectOutputStream(fout);
			oout.writeObject(listTransaction);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeInfo(int index) {
		try {
			logger.debug("removeInfo");

			FileInputStream fin = new FileInputStream(TRANSACTION_FILE);
			ObjectInputStream oin = new ObjectInputStream(fin);
			listTransaction = (ArrayList<Transaction>) oin.readObject();

			listTransaction.remove(index);

			FileOutputStream fout = new FileOutputStream(TRANSACTION_FILE);
			ObjectOutputStream oout = new ObjectOutputStream(fout);
			oout.writeObject(listTransaction);
		} catch (Exception e) {
			listTransaction = new ArrayList<Transaction>();
		}

	}

	@Override
	public int searchInfoID(Transaction obj) {
		try {
			logger.debug("searchInfoID");
			FileInputStream fin = new FileInputStream(TRANSACTION_FILE);
			ObjectInputStream oin = new ObjectInputStream(fin);
			folder1 = (ArrayList<Transaction>) oin.readObject();

			for (int p = 0; p < folder1.size(); p++) {
				if ((obj.getAccountID()).equals(folder1.get(p).getAccountID().trim())) {
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
