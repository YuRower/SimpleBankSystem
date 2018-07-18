package system.myBank.app;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DeleteRSerial
{
    ArrayList<Registration> listReg;
   

    public DeleteRSerial(int index)
    {
        try{
		
            FileInputStream fin2=new FileInputStream("Register.dat");
            ObjectInputStream oin2=new ObjectInputStream(fin2);
            listReg=(ArrayList<Registration>)oin2.readObject();

            listReg.remove(index);

		
            FileOutputStream fout2=new FileOutputStream("Register.dat");
            ObjectOutputStream oout2=new ObjectOutputStream(fout2);
            oout2.writeObject(listReg);


        }catch(Exception e)
        {
            listReg=new ArrayList<Registration>();
        }

    }
}
