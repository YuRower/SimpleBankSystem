package system.myBank.app;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class DepositSerial
{
    ArrayList<Transaction> lst;
    private final int OVERDRAFT=-100;
    private int crdt;
DepositSerial(){}

    public DepositSerial(Transaction cr,int index)
    {
        try
       {
            FileInputStream fin=new FileInputStream("Banking.dat");
            ObjectInputStream oin=new ObjectInputStream(fin);
            lst=(ArrayList<Transaction>)oin.readObject();

            int fix1=convert1(index);
            int calc=fix1+(Integer.parseInt(cr.getdepositamount()));
            String fix2= lst.get(index).getaccountno();
           
            String s= String.valueOf(calc);
            lst.get(index).setdepositamount(s);

       }catch(Exception e)
       {
            e.printStackTrace();
        }
        
        try
        {
            FileOutputStream fout=new FileOutputStream("Banking.dat");
            ObjectOutputStream oout=new ObjectOutputStream(fout);
            oout.writeObject(lst);
        }catch(Exception e){}

    }
    public int convert1(int index){
        int conv=Integer.parseInt(lst.get(index).getdepositamount());
        return(conv);

    }

}
