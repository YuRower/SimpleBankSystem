package system.myBank.app.storage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import system.myBank.app.entity.Registration;

public class RegistrDetailStorage {
	ArrayList<Registration> list4;

	public RegistrDetailStorage(Registration rg1) {
		try {
			FileInputStream fin = new FileInputStream("Register.dat");
			ObjectInputStream oin = new ObjectInputStream(fin);
			list4 = (ArrayList<Registration>) oin.readObject();
		} catch (Exception e) {
			list4 = new ArrayList<Registration>();
		}

		list4.add(rg1);

		try {
			FileOutputStream fout = new FileOutputStream("Register.dat");
			ObjectOutputStream oout = new ObjectOutputStream(fout);
			oout.writeObject(list4);
		} catch (Exception e) {
		}

	}

}
