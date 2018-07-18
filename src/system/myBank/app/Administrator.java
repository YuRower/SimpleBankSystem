package system.myBank.app;

import java.awt.Color;
import java.io.*;
import java.util.*;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;

public class Administrator extends JFrame implements ActionListener{

    private JLabel lI,lID,pas,skip;
    private JTextField tid;
    private JPasswordField tpass;
    private JButton bok;
    private int enterValidN=0,enterValidNum=0,ok=0;



    public Administrator(String title){
        super(title);
        Container c=getContentPane();
        c.setLayout(new GridLayout(4,2));

        bok=new JButton("OK");
        bok.addActionListener(this);

        
        lI=new JLabel("Log In");
      
        lI.setForeground(Color.BLUE);

       
        lID=new JLabel("Login ID:");
        
        lID.setForeground(Color.BLACK);
        tid=new JTextField(20);

        pas=new JLabel("Password:");
        
        pas.setForeground(Color.BLACK);
        tpass=new JPasswordField(20);

        skip=new JLabel("");

        c.add(lI);c.add(new JLabel(""));
        c.add(lID);c.add(tid);
        c.add(pas);c.add(tpass);
        c.add(skip);c.add(bok);

        setSize(450, 200);
        setLocation(300,200);
        setResizable(false);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==bok) {
            String id1 = tid.getText();
            String pass1 = tpass.getText();

            if (id1.equals("admin") && pass1.equals("1234"))
                do {
                    String s1 = tid.getText();
                    String reg = "^[a-zA-z0-9_@]";
                    Scanner sc = new Scanner(s1);
                    String result = sc.findInLine(reg);

                    if (result == null) {
                        tid.setText("");
                        JOptionPane.showMessageDialog(this, "Enter Valid Name..");
                    } else {
                        enterValidN = 1;
                    }

                    String s2 = tpass.getText();
                    String reg1 = "^[0-9]";
                    Scanner sc1 = new Scanner(s2);
                    String result1 = sc1.findInLine(reg1);
                    if (result1 == null) {
                        tpass.setText("");
                        JOptionPane.showMessageDialog(this, "INVALID...");
                    } else {
                        enterValidNum = 1;
                    }

                    if (enterValidN == 1) {
                        if (enterValidNum == 1) {
                            ok = 1;
                            break;
                        }
                    }
                } while (ok == 1);

            if (ok == 1) {
                   SignIn.accessGranted=true;
                    new SignIn(" Signed as an Administrator ");
                }
			else
                {
                    JOptionPane.showMessageDialog(this, "Wrong user name or password please re enter");
                    tpass.setText("");
                    tid.setText(" ");
                }

            }
        }


    public static void main(String args[])
    {
        new Administrator("Administrator");

    }

}
