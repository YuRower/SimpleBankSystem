package system.myBank.app;


import java.awt.Color;
import java.util.*;
import java.awt.Container;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;

public class Register extends JFrame implements ActionListener{
public static String  name;
ArrayList arrlist;
	
    private JLabel lName,lDob,lGender,lPhoneN,lAdress,lEmail,lAccType,lDepAmo,l12,laccNo,regstr;
    public  JTextField tname;
	private JTextField tPhoneNo;
	private JTextField tAddress;
	private JTextField tEmailId;
	private JTextField tDepositAmount;
	private JTextField tAccountNo;
    private JButton bSubmit, bReset, bSearch;
    private JComboBox day,month,year;

    private JRadioButton rmale,rfemale, rsaving, rcurrent;
    private int a,a1,validName=0,validAddress=0,validPhone=0,validEmail=0,x=0,validDepositAm=0,validAccN=0,withdrawalamount=0;

    ArrayList<Registration> list;
    ArrayList<Transaction> folder;
    ArrayList<TransactionInfo> match;


    public Register(String title)
    {
        super(title);
        list=new ArrayList<Registration>();
        match=new ArrayList<TransactionInfo>();
        folder=new ArrayList<Transaction>();


        Container c=getContentPane();
        c.setLayout(new GridLayout(16,2));

        tname=new JTextField(20);
        tPhoneNo =new JTextField(20);
        tAddress=new JTextField(20);
        tEmailId =new JTextField(20);

        tDepositAmount=new JTextField(20);
        tAccountNo =new JTextField(20);

        rmale=new JRadioButton("Male");
        rmale.addActionListener(this);
        rmale.setSelected(true);

        rfemale=new JRadioButton("Female");
        rfemale.addActionListener(this);
        rfemale.setSelected(true);







        rsaving=new JRadioButton("saving");
        rsaving.addActionListener(this);
        rsaving.setSelected(true);

        rcurrent=new JRadioButton("current");
        rcurrent.addActionListener(this);
        rcurrent.setSelected(true);



        ButtonGroup rgroup=new ButtonGroup();

        rgroup.add(rmale);
        rgroup.add(rfemale);
        JPanel gpanel=new JPanel();

        gpanel.add(rmale);
        gpanel.add(rfemale);




        ButtonGroup rgroup2=new ButtonGroup();

        rgroup2.add(rsaving);
        rgroup2.add(rcurrent);

        JPanel gpanel2=new JPanel();

        gpanel2.add(rsaving);
        gpanel2.add(rcurrent);


        String dvalue[]=new String[31];
        for(int i=0;i<=30;i++)
        {
            dvalue[i]=String.valueOf(i+1);
        }
        day=new JComboBox(dvalue);

        String mvalue[]=new String[12];
        for(int i=0;i<=11;i++)
        {
            mvalue[i]=String.valueOf(i+1);
        }
        month=new JComboBox(mvalue);

        String yvalue[]=new String[112];
        int cnt=0;
        for(int i=1900;i<=2011;i++)
        {
            yvalue[cnt]=String.valueOf(i);
            cnt++;
        }
        year=new JComboBox(yvalue);
        JPanel cpanel=new JPanel();
        cpanel.add(day);
        cpanel.add(month);
        cpanel.add(year);
    
        regstr=new JLabel("Registration");
       
        regstr.setForeground(Color.BLACK);

        bSubmit =new JButton("Create");
        bSubmit.addActionListener(this);


        bReset =new JButton("Show All");
        bReset.addActionListener(this);


  

        bSearch =new JButton("Search");
        bSearch.addActionListener(this);



       

        lName=new JLabel("   Name:");
        
        lName.setForeground(Color.BLUE);

        lDob=new JLabel("   DOB:");
       
        lDob.setForeground(Color.BLUE);

        lGender=new JLabel("   Gender:");
        
        lGender.setForeground(Color.BLUE);

        lPhoneN=new JLabel("   Phone N.:");
       
        lPhoneN.setForeground(Color.BLUE);

        lAdress=new JLabel("   Address:");
        
        lAdress.setForeground(Color.BLUE);

        lEmail=new JLabel("   Email id:");
        
        lEmail.setForeground(Color.BLUE);



        lAccType=new JLabel("   Account type:");
        lAccType.setForeground(Color.BLUE);

        lDepAmo=new JLabel("   Deposit Amount:");
        lDepAmo.setForeground(Color.BLUE);


        laccNo=new JLabel("   Account N.:");
        laccNo.setForeground(Color.BLUE);

        c.add(regstr);c.add(new JLabel(""));
        c.add(lName);c.add(tname);
        c.add(lDob);c.add(cpanel);
        c.add(lGender);c.add(gpanel);
        c.add(lPhoneN);c.add(tPhoneNo);
        c.add(lAdress);c.add(tAddress);
        c.add(lEmail);c.add(tEmailId);

        c.add(lAccType);c.add(gpanel2);
        c.add(lDepAmo);c.add(tDepositAmount);

        c.add(laccNo);c.add(tAccountNo);
        c.add(bSubmit);
        c.add(new JLabel(""));
        c.add(bReset);
        c.add(new JLabel(""));
        c.add(bSearch);
    
        setSize(800, 650);
        setLocation(200,40);
        setResizable(false);
       
        setVisible(true);


    }

    public void actionPerformed(ActionEvent ae) {
        int f=-1;

        String name=tname.getText();
      
        String phoneno= tPhoneNo.getText();
        String address=tAddress.getText();
        String emailid= tEmailId.getText();
        String depositamount=tDepositAmount.getText();
        String accountno= tAccountNo.getText();
    
        
        String gender="";
        if(rmale.isSelected()) {
            gender="Male";
        }
        else if(rfemale.isSelected()){
            gender="Female";
        }

        String accounttype="";
        if(rsaving.isSelected()){
            accounttype="Saving";
        }
        else if(rcurrent.isSelected()){
            accounttype="Current";
        }

        String d=(String)day.getSelectedItem();
        String m=(String)month.getSelectedItem();
        String y=(String)year.getSelectedItem();
        String dob=d + "-" + m + "-" + y;






        Registration r=new Registration(name, phoneno, address, emailid,  depositamount, accountno, gender, accounttype, dob);
        Transaction ts=new Transaction(depositamount, accountno);



        SearchRinSerial scr = new SearchRinSerial();
        a = scr.searchaccountno(r);

        if(ae.getSource()==rmale) {
            JOptionPane.showMessageDialog(this, "Your Gender : Male");
        }
        else if(ae.getSource()==rfemale){
            JOptionPane.showMessageDialog(this, "Your Gender : Female");

        }

        else if(ae.getSource()==rsaving){


            JOptionPane.showMessageDialog(this, "Your account type : Saving");
        }

        else if(ae.getSource()==rcurrent){
            JOptionPane.showMessageDialog(this, "Your account type : Current");
        }



        if(ae.getSource()== bSubmit){

            int con=JOptionPane.showConfirmDialog(this, "Are You Sure to Register?");

            if(con==JOptionPane.YES_OPTION){

                String s1 = tname.getText();
                String reg1="^[a-zA-Z.]";
                Scanner sc1 = new Scanner(s1);
                String result1 =sc1.findInLine(reg1);

                if(result1 == null){
                    tname.setText("");
                    JOptionPane.showMessageDialog(this,"Enter Valid Name..");
                    validName=1;
                    if((validName==0)&&(validAddress==0)&&(validPhone==0))
                        JOptionPane.showMessageDialog(this,"Your data saved..");
                }

                String s2 = tPhoneNo.getText();
                String reg2="\\d{10}";
                Scanner sc2 = new Scanner(s2);
                String result2 =sc2.findInLine(reg2);

                if(result2 == null){
                    tPhoneNo.setText("");
                    JOptionPane.showMessageDialog(this,"Enter Valid phone no...");
                    validPhone=1;
                    if((validName==0)&&(validAddress==0)&&(validPhone==0))
                        JOptionPane.showMessageDialog(this,"Your data saved..");
                }

                String s3 = tAddress.getText();
                String reg3="^[a-zA-Z0-9.,]";
                Scanner sc3 = new Scanner(s3);
                String result3 =sc3.findInLine(reg3);
                if(result3 == null){
                    tAddress.setText("");
                    JOptionPane.showMessageDialog(this,"Enter valid Address...");
                    validAddress=1;
                    if((validName==0)&&(validAddress==0)&&(validPhone==0))
                        JOptionPane.showMessageDialog(this,"Your data saved..");
                }




                String s4 = tEmailId.getText();
                String reg4="^[a-zA-Z0-9_@.]";
                Scanner sc4 = new Scanner(s4);
                String result4 =sc4.findInLine(reg4);
                if(result4 == null){
                    tEmailId.setText("");
                    JOptionPane.showMessageDialog(this,"Enter valid emailid...");
                    validEmail=1;
                    if((validName==0)&&(validAddress==0)&&(validPhone==0)&&(validEmail==0))
                        JOptionPane.showMessageDialog(this,"Your data saved..");
                }





                String s6 = tDepositAmount.getText();
                String reg6="^\\d+$";
                Scanner sc6 = new Scanner(s6);
                String result6 =sc6.findInLine(reg6);
                if(result6 == null){
                    tDepositAmount.setText("");
                    JOptionPane.showMessageDialog(this,"Enter valid Deposit Amount....");
                    validDepositAm=1;
                    if((validName==0)&&(validAddress==0)&&(validPhone==0)&&(validEmail==0)&&(x==0)&&(validDepositAm==0))
                        JOptionPane.showMessageDialog(this,"Your data saved..");
                }



                String s7 = tAccountNo.getText();
                String reg7="^\\d+$";
                Scanner sc7 = new Scanner(s7);
                String result7 =sc7.findInLine(reg7);
                if(result7 == null) {
                    tAccountNo.setText("");
                    JOptionPane.showMessageDialog(this,"Enter valid Account no. ....");
                    validAccN=1;
                    if((validName==0)&&(validAddress==0)&&(validPhone==0)&&(validEmail==0)&&(x==0)&&(validDepositAm==0)&&(validAccN==0))
                        JOptionPane.showMessageDialog(this,"Your data saved..");
                }

                if((result1 != null) && (result2 != null) && (result3 != null)&& (result4 !=null)&&(result6!=null)&&(result7!=null)){
                    if(a!=-1)
                    {
                        JOptionPane.showMessageDialog(this,"This account already exists. Please enter another no.acc...");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this,"Your data saved successfully...");
                        new RegistrInfoSerial(r);
                        new TransAddInfoSerial(ts);
                    }
                }

            }
        }

        else if(ae.getSource()== bReset)
        {
            new CustomerInfo();
        }


        else if(ae.getSource()== bSearch)
        {
            if(a == -1)
            {
                JOptionPane.showMessageDialog(this, "NO DATA FOUND");
            }
            else
            {
                new SearchDisp(a);
            }}

        
    }


    public static void main(String args[])

    {
        new Register("Register...");

    }

}







