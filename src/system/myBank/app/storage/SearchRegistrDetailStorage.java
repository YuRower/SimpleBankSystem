package system.myBank.app.storage;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import system.myBank.app.entity.Registration;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.*;
import java.util.*;

public class SearchRegistrDetailStorage {
	private ArrayList<Registration> folder2;
	private int f = -1;


	public int searchAcountID(Registration rn) {
		try {
			FileInputStream fin = new FileInputStream("Register.dat");
			ObjectInputStream oin = new ObjectInputStream(fin);
			folder2 = (ArrayList<Registration>) oin.readObject();

			for (int p = 0; p < folder2.size(); p++) {
				if ((rn.getAccountID()).equals(folder2.get(p).getAccountID().trim())) {
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
