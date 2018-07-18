package system.myBank.app;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DeleteSerial
{
  
    ArrayList<Transaction> lst;

    public DeleteSerial(int index) {
    {
        try{
            FileInputStream fin=new FileInputStream("Banking.dat");
            ObjectInputStream oin=new ObjectInputStream(fin);
            lst=(ArrayList<Transaction>)oin.readObject();


            lst.remove(index);


            FileOutputStream fout=new FileOutputStream("Banking.dat");
            ObjectOutputStream oout=new ObjectOutputStream(fout);
            oout.writeObject(lst);
        }catch(Exception e)
        {
            lst=new ArrayList<Transaction>();
        }

    }
   
    }

	
}

	

	

