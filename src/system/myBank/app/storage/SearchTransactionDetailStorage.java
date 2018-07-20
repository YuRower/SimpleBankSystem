package system.myBank.app.storage;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import system.myBank.app.entity.Transaction;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.*;
import java.util.*;
public class SearchTransactionDetailStorage
{
    private ArrayList<Transaction> folder1;
    private int f = -1;
    public int searchaccountno(Transaction tsn)
    {
        try
        {
            FileInputStream fin=new FileInputStream("Banking.dat");
            ObjectInputStream oin=new ObjectInputStream(fin);
            folder1=(ArrayList<Transaction>)oin.readObject();

            for(int p=0; p<folder1.size(); p++)
            {
				if ((tsn.getAccountID()).equals(folder1.get(p).getAccountID().trim()))
                {
                    f = p;
                    break;
                }
            }

            return(f);

        }catch(Exception e)
        {
            return(-1);

        }

    }
}