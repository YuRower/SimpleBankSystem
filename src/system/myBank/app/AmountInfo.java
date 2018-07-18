package system.myBank.app;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;



import java.awt.BorderLayout;
import java.awt.Container;
import java.io.*;
import java.util.*;



public class AmountInfo extends JFrame {
	ArrayList<Transaction> folder;
//	public AmountInfo getFolder;

    public AmountInfo()
    {
        super("Amount Details");
        String heading[]={"DepositAmount","AccountN"};
        String data[][]=new String[20][2];
       folder=new ArrayList<Transaction>();
        try {
            FileInputStream fin=new FileInputStream("Banking.dat");
            ObjectInputStream oin=new ObjectInputStream(fin);
            folder=(ArrayList<Transaction>)oin.readObject();

            int cnt=0,c=0;
            for(Transaction tn : folder)
            {

                data[cnt][0]=tn.getdepositamount();
                data[cnt][1]=tn.getaccountno();

                cnt++;
                c=0;
            }
            try   {
                FileOutputStream fout=new FileOutputStream("Banking.dat");
                ObjectOutputStream oout=new ObjectOutputStream(fout);
                oout.writeObject(folder);
            }catch(Exception e){}
            
            Container con=getContentPane();
            con.setLayout(new BorderLayout());

            JTable datatable=new JTable(data, heading);
            JScrollPane jsp=new JScrollPane(datatable);

            con.add(new JLabel("All Customer Details"),BorderLayout.NORTH);
            con.add(jsp,BorderLayout.CENTER);

            setSize(250, 300);

            setLocation(200, 200);
            setVisible(true);

        }catch(Exception e)
        {
            e.printStackTrace();
        }

    }
   

    public static void main(String args[])
    {
        new AmountInfo();
    }

}
