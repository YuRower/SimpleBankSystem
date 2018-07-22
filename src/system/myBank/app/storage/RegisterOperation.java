package system.myBank.app.storage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import system.myBank.app.entity.Registration;

public class RegisterOperation extends AbstractBankOperation<Registration> {
	private final String REGISTR_FILE = "Register.dat";

	ArrayList<Registration> listReg;

	private int f = -1;

	public int searchInfoID(Registration rn) {
		try {
			System.out.println("searchInfoID");
			FileInputStream fin = new FileInputStream(REGISTR_FILE);
			ObjectInputStream oin = new ObjectInputStream(fin);
			listReg = (ArrayList<Registration>) oin.readObject();

			for (int p = 0; p < listReg.size(); p++) {
				if ((rn.getAccountID()).equals(listReg.get(p).getAccountID().trim())) {
					f = p;
					break;
				}
			}

			return (f);

		} catch (Exception e) {
			return (-1);

		}

	}

	public void removeInfo(int index) {
		try {
			System.out.println("removeInfo");

			FileInputStream fin2 = new FileInputStream(REGISTR_FILE);
			ObjectInputStream oin2 = new ObjectInputStream(fin2);
			listReg = (ArrayList<Registration>) oin2.readObject();

			listReg.remove(index);

			FileOutputStream fout2 = new FileOutputStream(REGISTR_FILE);
			ObjectOutputStream oout2 = new ObjectOutputStream(fout2);
			oout2.writeObject(listReg);

		} catch (Exception e) {
			listReg = new ArrayList<Registration>();
		}

	}

	public void addInfo(Registration rg1) {
		try {
			System.out.println("addInfo");

			FileInputStream fin = new FileInputStream(REGISTR_FILE);
			ObjectInputStream oin = new ObjectInputStream(fin);
			listReg = (ArrayList<Registration>) oin.readObject();
		} catch (Exception e) {
			listReg = new ArrayList<Registration>();
		}

		listReg.add(rg1);

		try {
			FileOutputStream fout = new FileOutputStream(REGISTR_FILE);
			ObjectOutputStream oout = new ObjectOutputStream(fout);
			oout.writeObject(listReg);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
