package system.myBank.app;

import java.util.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class WithdrawSerial
{
    ArrayList<Transaction> lst;

    private int crdt;
    private final int OVERDRAFT=-100;


    public WithdrawSerial(Transaction cr,int index)
    {
        try
        {
            FileInputStream fin=new FileInputStream("Banking.dat");
            ObjectInputStream oin=new ObjectInputStream(fin);
            lst=(ArrayList<Transaction>)oin.readObject();

            int fix1=convert(index);
            int calc=fix1-(Integer.parseInt(cr.getdepositamount()));
            if(calc < OVERDRAFT){
            	JOptionPane.showMessageDialog(null,new String("Operation could not be completed"));
            	return;
            }
            String s= String.valueOf(calc);

            lst.get(index).setdepositamount(s);

        }catch(Exception e)
        {
            lst=new ArrayList<Transaction>();
        }
       
        try{
            FileOutputStream fout=new FileOutputStream("Banking.dat");
            ObjectOutputStream oout=new ObjectOutputStream(fout);
            oout.writeObject(lst);
        }catch(Exception e){}

    }
    public int convert(int index)    {
        int conv=Integer.parseInt(lst.get(index).getdepositamount());
        return(conv);

    }

}